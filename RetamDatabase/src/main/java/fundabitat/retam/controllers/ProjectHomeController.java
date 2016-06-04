/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Descriptor;
import fundabitat.retam.persistence.PersistenceManager;
import fundabitat.retam.utils.Function;
import fundabitat.retam.utils.ListViewCellUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TitledPane subDescriptorPane;

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
    }

    private void setupCountries(EntityManager eManager) {
        Query findAllCountries = eManager.createNamedQuery("Country.findAll");
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

                    // change.getAddedSubList() is buggy as hell.
                    // Why did I choose JavaFx again?
                    List<? extends Descriptor> selected = change.getList();

                    // Find the difference of the lists
                    // Copy the list, we don't want to delete the items
                    // in the original list
                    List<Descriptor> clonedList = new ArrayList(selected);

                    clonedList.removeAll(selectedDescriptors);

                    for (Descriptor d : clonedList) {
                        System.out.println("added: " + d.getName());
                        selectedDescriptors.add(d);
                    }

                } else {

                    List<? extends Descriptor> removed = change.getRemoved();

                    for (Descriptor d : removed) {
                        System.out.println("removed: " + d.getName());
                        selectedDescriptors.remove(d);
                    }

                }

            }
        });
    }

}
