package fundabitat.retam.controllers;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Project;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
    
    @FXML private TableView<Project> projectTable;
    @FXML private TableColumn<Project, String> projectCode;
    @FXML private TableColumn<Project, String> projectName;
    @FXML private TableColumn<Project, String> projectCountry;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        final ObservableList<Project> list = FXCollections.observableArrayList();
        
        Country c = new Country();
        c.setIdCountry(5);
        c.setName("Venezuela");
        
        Project p = new Project();
        p.setCode("665");
        p.setName("nombre");
        
        p.setIdCountry(c);
        list.add(p);
        
        projectCode.setCellValueFactory(
            new PropertyValueFactory<Project,String>("code"));
        projectName.setCellValueFactory(
            new PropertyValueFactory<Project,String>("name"));
        projectCountry.setCellValueFactory(
            new PropertyValueFactory<Project,String>("countryName"));
        
        projectTable.setItems(list);
    }    
}
