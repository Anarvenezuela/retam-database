/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Descriptor;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.SubDescriptor;
import fundabitat.retam.persistence.PersistenceManager;
import fundabitat.retam.utils.Function;
import fundabitat.retam.utils.ListUtil;
import fundabitat.retam.utils.ListViewCellUtil;
import fundabitat.retam.utils.ListViewUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ProjectHomeController implements Initializable {

    @FXML
    private ListView<Country> countryList;
    @FXML
    private ListView<Descriptor> descriptorList;
    @FXML
    private Accordion subDescriptorAccordion;

    private List<Country> countries;
    private List<Descriptor> descriptors;
    private List<SubDescriptor> subDescriptors;

    // Used to keep the previous selected values because JavaFx is just too buggy.
    private List<Descriptor> selectedDescriptors = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();
        setupCountries(eManager);
        setupDescriptors(eManager);
        setupSubDescriptors(eManager);
        eManager.close();
    }

    /**
     * Sets up the countries' panel. Enables the multiselection hack and sets
     * the display text
     */
    private void setupCountries(EntityManager eManager) {
        Query findAllCountries = eManager.createNamedQuery("Country.findProjectCountries");
        countries = findAllCountries.getResultList();

        ObservableList<Country> observableCountryList;
        observableCountryList = FXCollections.observableArrayList(countries);
        countryList.setItems(observableCountryList);

        ListViewCellUtil.setupCell(countryList, new Function<Country, String>() {
            @Override
            public String apply(Country input) {
                return input.getName();
            }

        }, true);
    }

    /**
     * Sets up the descriptor panel. Enables the multiselection hack, sets the
     * display text and adds the action to do on select.
     */
    private void setupDescriptors(EntityManager eManager) {
        Query findAllDescriptors = eManager.createNamedQuery("Descriptor.findAll");
        descriptors = findAllDescriptors.getResultList();

        ObservableList<Descriptor> observableCountryList;
        observableCountryList = FXCollections.observableArrayList(descriptors);
        descriptorList.setItems(observableCountryList);

        ListViewCellUtil.setupCell(descriptorList, new Function<Descriptor, String>() {
            @Override
            public String apply(Descriptor input) {
                return input.getName();
            }

        }, true);

        setupDescriptorSelectionAction();
    }

    /**
     * Gets the list of subdescriptor to be used in the search.
     *
     * @param eManager
     */
    private void setupSubDescriptors(EntityManager eManager) {
        Query findAllDescriptors = eManager.createNamedQuery("SubDescriptor.findAll");
        subDescriptors = findAllDescriptors.getResultList();
    }

    /**
     * Adds the action to do when selecting a descriptor. When a descriptor is
     * selected, a new panel with its subdescriptors should be added to the
     * subdescriptors pane. Selection can add or remove items.
     */
    private void setupDescriptorSelectionAction() {

        descriptorList.getSelectionModel().getSelectedItems().addListener(
                new ListChangeListener<Descriptor>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Descriptor> change) {

                change.next();

                if (change.wasAdded()) {
                    handleDescriptorAdd(change);
                } else {
                    handleDescriptorRemove(change);
                }
            }
        });
    }

    /**
     * Handles the selection of a descriptor. Calculate the difference between
     * the old list and the new one to get the added element. Then it adds the
     * panel
     *
     * @param change the change made by the user
     */
    private void handleDescriptorAdd(ListChangeListener.Change<? extends Descriptor> change) {
        // change.getAddedSubList() is buggy as hell.
        // Why did I choose JavaFx again?
        List<? extends Descriptor> selected = change.getList();

        // Find the difference of the lists
        // Copy the list, we don't want to delete the items
        // in the original list
        List<Descriptor> clonedList = new ArrayList(selected);

        clonedList.removeAll(selectedDescriptors);

        for (Descriptor d : clonedList) {
            selectedDescriptors.add(d);

            TitledPane tp = createSubdescriptorTitledPane(d);
            subDescriptorAccordion.getPanes().add(tp);
            subDescriptorAccordion.setExpandedPane(tp);
        }
    }

    /**
     * Creates a subdescriptor pane for the selected descriptor. It contains a
     * ListView with the subdescriptors.
     *
     * @param d the selected descriptor
     * @return the titled pane
     */
    private TitledPane createSubdescriptorTitledPane(Descriptor d) {

        TitledPane tp = new TitledPane();
        tp.setText(d.getName());

        ListView<SubDescriptor> listView = createSubdescriptorListView(d);

        tp.setContent(listView);
        return tp;
    }

    /**
     * Creates the ListView of subdescriptors.
     */
    private ListView<SubDescriptor> createSubdescriptorListView(Descriptor d) {

        ObservableList<SubDescriptor> items = FXCollections.observableArrayList();

        for (SubDescriptor sub : d.getSubDescriptorCollection()) {
            items.add(sub);
        }

        ListView<SubDescriptor> listView = new ListView(items);

        ListViewCellUtil.setupCell(listView, new Function<SubDescriptor, String>() {
            @Override
            public String apply(SubDescriptor input) {
                return input.getName();
            }
        }, true);

        return listView;
    }

    /**
     * Handles the deselection of a descriptor. Removes the corresponding pane.
     */
    private void handleDescriptorRemove(ListChangeListener.Change<? extends Descriptor> change) {
        List<? extends Descriptor> removed = change.getRemoved();

        for (Descriptor d : removed) {
            selectedDescriptors.remove(d);
            ObservableList<TitledPane> tpList = subDescriptorAccordion.getPanes();

            removeSubdescriptorTitledPane(tpList, d);
        }
    }

    /**
     * Removes the pane of subdescriptors
     *
     * @param tpList the list of panes
     * @param d the descriptor to remove
     */
    private void removeSubdescriptorTitledPane(ObservableList<TitledPane> tpList, Descriptor d) {

        for (TitledPane tp : tpList) {
            if (tp.getText().equals(d.getName())) {
                tpList.remove(tp);
                break;
            }
        }
    }

    /**
     * Gets a list of the ids the selected countries.
     */
    private List<Integer> getSelectedCountriesIds() {

        List<Integer> ids = ListViewUtil.mapSelected(countryList, new Function<Country, Integer>() {
            @Override
            public Integer apply(Country input) {
                return input.getIdCountry();
            }
        });

        return ids;
    }

    /**
     * Gets a list of the ids the selected descriptors.
     */
    private List<Integer> getSelectedDescriptorsIds() {

        List<Integer> ids = ListViewUtil.mapSelected(descriptorList, new Function<Descriptor, Integer>() {
            @Override
            public Integer apply(Descriptor input) {
                return input.getIdDescriptor();
            }
        });

        return ids;
    }

    /**
     * Gets a list of the ids the selected subdescriptors.
     */
    private List<Integer> getSelectedSubDescIds() {

        ObservableList<TitledPane> tpList = subDescriptorAccordion.getPanes();
        List<Integer> ids = new ArrayList();

        for (TitledPane tp : tpList) {

            // Do not take into account the panel used as title
            if (!tp.isCollapsible()) {
                continue;
            }

            ListView<SubDescriptor> listView = (ListView) tp.getContent();

            List<Integer> listViewIds;
            listViewIds = ListViewUtil.mapSelected(listView, new Function<SubDescriptor, Integer>() {
                @Override
                public Integer apply(SubDescriptor input) {
                    return input.getIdSubDescriptor();
                }
            });

            ids.addAll(listViewIds);
        }

        return ids;
    }

    /**
     * Action triggered when a search is clicked.
     */
    @FXML
    public void onActionSearchButton(ActionEvent event) {
        List<Integer> descIds = getSelectedDescriptorsIds();
        List<Integer> subDescIds = getSelectedSubDescIds();
        List<Integer> countriesIds = getSelectedCountriesIds();

        countriesIds = emptyItemSearch(countriesIds, countries, new Function<Country, Integer>() {
            @Override
            public Integer apply(Country input) {
                return input.getIdCountry();
            }
        });

        descIds = emptyItemSearch(descIds, descriptors, new Function<Descriptor, Integer>() {
            @Override
            public Integer apply(Descriptor input) {
                return input.getIdDescriptor();
            }
        });

        subDescIds = emptyItemSearch(subDescIds, subDescriptors, new Function<SubDescriptor, Integer>() {
            @Override
            public Integer apply(SubDescriptor input) {
                return input.getIdSubDescriptor();
            }
        });

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query filterProject = eManager.createNamedQuery("Project.filterProjects")
                .setParameter("descs", descIds)
                .setParameter("subs", subDescIds)
                .setParameter("countries", countriesIds);

        List<Project> projects = filterProject.getResultList();

        for (Project p : projects) {
            System.out.println(p.getName());
        }

        eManager.close();
    }

    /**
     * Handles the empty selection. When no item is selected it should search
     * for any item.
     */
    private <E> List<Integer> emptyItemSearch(List<Integer> selectedIds, List<E> elems, Function<E, Integer> f) {
        if (selectedIds.isEmpty()) {
            selectedIds = ListUtil.map(elems, f);
        }
        return selectedIds;
    }
}
