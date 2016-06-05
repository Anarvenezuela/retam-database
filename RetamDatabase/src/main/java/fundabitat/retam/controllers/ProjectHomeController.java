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
        eManager.close();
    }

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

    private TitledPane createSubdescriptorTitledPane(Descriptor d) {

        TitledPane tp = new TitledPane();
        tp.setText(d.getName());

        ListView<SubDescriptor> listView = createSubdescriptorListView(d);

        tp.setContent(listView);
        return tp;
    }

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

    private void handleDescriptorRemove(ListChangeListener.Change<? extends Descriptor> change) {
        List<? extends Descriptor> removed = change.getRemoved();

        for (Descriptor d : removed) {
            selectedDescriptors.remove(d);
            ObservableList<TitledPane> tpList = subDescriptorAccordion.getPanes();

            removeSubdescriptorTitledPane(tpList, d);
        }
    }

    private void removeSubdescriptorTitledPane(ObservableList<TitledPane> tpList, Descriptor d) {

        for (TitledPane tp : tpList) {

            if (tp.getText().equals(d.getName())) {
                tpList.remove(tp);
                break;
            }
        }
    }

    private List<Integer> getSelectedDescriptorsIds() {

        List<Integer> ids = ListViewUtil.mapSelected(descriptorList, new Function<Descriptor, Integer>() {
            @Override
            public Integer apply(Descriptor input) {
                return input.getIdDescriptor();
            }
        });

        return ids;
    }

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

    @FXML
    public void onActionSearchButton(ActionEvent event) {
        List<Integer> descIds = getSelectedDescriptorsIds();
        List<Integer> subDescIds = getSelectedSubDescIds();

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query filterProject;

        if (subDescIds.isEmpty()) {
            filterProject = eManager.createNamedQuery("Project.filterProjectsByDescs")
                    .setParameter("descs", descIds);
        } else {

            filterProject = eManager.createNamedQuery("Project.filterProjects")
                    .setParameter("descs", descIds)
                    .setParameter("subs", subDescIds);
        }

        List<Project> projects = filterProject.getResultList();

        for (Project p : projects) {
            System.out.println(p.getName());
        }

        eManager.close();
    }
}
