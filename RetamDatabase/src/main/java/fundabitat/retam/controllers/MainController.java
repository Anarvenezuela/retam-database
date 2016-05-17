package fundabitat.retam.controllers;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Project;
import fundabitat.retam.persistence.PersistenceManager;
import java.io.IOException;
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

public class MainController implements Initializable {

    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectCode;
    @FXML
    private TableColumn<Project, String> projectName;
    @FXML
    private TableColumn<Project, String> projectCountry;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query findAllProjects = eManager.createNamedQuery("Project.findAll");
        List<Project> list = findAllProjects.getResultList();

        final ObservableList<Project> observableList = FXCollections.observableArrayList(list);

        projectCode.setCellValueFactory(
                new PropertyValueFactory<Project, String>("code"));
        projectName.setCellValueFactory(
                new PropertyValueFactory<Project, String>("name"));
        projectCountry.setCellValueFactory(
                new PropertyValueFactory<Project, String>("countryName"));

        projectTable.setItems(observableList);
    }
}
