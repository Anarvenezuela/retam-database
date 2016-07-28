/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.Beneficiary;
import fundabitat.retam.models.Project;
import java.net.URL;
import java.util.ArrayList;
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
public class BeneficiaryInfoController implements Initializable, ProjectSceneInfoController {

    @FXML
    private TableView<Beneficiary> beneficiaryTable;
    @FXML
    private TableColumn<Beneficiary, String> beneficiaryCode;
    @FXML
    private TableColumn<Beneficiary, String> beneficiaryName;

    private List<Beneficiary> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initData(Project p) {
        list = new ArrayList(p.getBeneficiaryCollection());

        beneficiaryCode.setCellValueFactory(
                new PropertyValueFactory<Beneficiary, String>("code"));
        beneficiaryName.setCellValueFactory(
                new PropertyValueFactory<Beneficiary, String>("name"));

        final ObservableList<Beneficiary> observableList = FXCollections.observableArrayList(list);
        beneficiaryTable.setItems(observableList);
    }
}
