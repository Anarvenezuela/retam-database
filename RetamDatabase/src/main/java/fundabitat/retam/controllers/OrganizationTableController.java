/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.controllers.interfaces.ChildrenControllerInterface;
import fundabitat.retam.controllers.interfaces.ParentControllerInterface;
import fundabitat.retam.models.Organization;
import fundabitat.retam.persistence.PersistenceManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class OrganizationTableController implements Initializable, ChildrenControllerInterface {

    private ParentControllerInterface parent;

    @FXML
    private TableView<Organization> organizationTable;
    @FXML
    private TableColumn<Organization, String> organizationCode;
    @FXML
    private TableColumn<Organization, String> organizationName;
    @FXML
    private TableColumn<Organization, String> organizationCountry;

    private List<Organization> list = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        organizationCode.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("code"));
        organizationName.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("name"));
        organizationCountry.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("countryName"));

        loadOrganizations();

        final ObservableList<Organization> observableList = FXCollections.observableArrayList(list);

        organizationTable.setItems(observableList);
    }

    private void loadOrganizations() {

        if (list == null) {
            PersistenceManager pManager = PersistenceManager.getInstance();
            EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

            Query findAllOrganization = eManager.createNamedQuery("Organization.findAll");
            list = findAllOrganization.getResultList();
            eManager.close();
        }

    }

    @Override
    public void addParentController(ParentControllerInterface ctrl) {
        parent = ctrl;
    }

    @FXML
    public void onActionBackButton(ActionEvent event) {
        parent.popPane();
    }

}
