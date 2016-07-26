/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.models.Organization;
import fundabitat.retam.models.Participation;
import fundabitat.retam.models.Project;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ParticipationInfoController implements Initializable {

    @FXML
    private TableView<Organization> organizationTable;
    @FXML
    private TableColumn<Organization, String> organizationCode;
    @FXML
    private TableColumn<Organization, String> organizationName;
    @FXML
    private TableColumn<Organization, String> organizationCountry;

    private List<Organization> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(Project p) {

        Collection<Participation> participations = p.getParticipationCollection();
        list = Participation.getOrgFromCollection(participations);

        organizationCode.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("code"));
        organizationName.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("name"));
        organizationCountry.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("countryName"));

        final ObservableList<Organization> observableList = FXCollections.observableArrayList(list);
        organizationTable.setItems(observableList);
    }

}
