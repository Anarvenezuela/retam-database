/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.controllers.interfaces.ChildrenControllerInterface;
import fundabitat.retam.controllers.interfaces.ParentControllerInterface;
import fundabitat.retam.controllers.projectScene.ProjectSceneController;
import fundabitat.retam.models.Project;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ProjectTableController implements Initializable, ChildrenControllerInterface {

    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectCode;
    @FXML
    private TableColumn<Project, String> projectName;
    @FXML
    private TableColumn<Project, String> projectCountry;

    private ParentControllerInterface parentCtrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        projectCode.setCellValueFactory(
                new PropertyValueFactory<Project, String>("code"));
        projectName.setCellValueFactory(
                new PropertyValueFactory<Project, String>("name"));
        projectCountry.setCellValueFactory(
                new PropertyValueFactory<Project, String>("countryName"));
    }

    public void initProjects(List<Project> list) {
        final ObservableList<Project> observableList = FXCollections.observableArrayList(list);
        projectTable.setItems(observableList);
    }

    @Override
    public void addParentController(ParentControllerInterface ctrl) {
        parentCtrl = ctrl;
    }

    @FXML
    public void onClickProject(MouseEvent event) {

        if (event.getClickCount() == 2) {

            Project project = projectTable.getSelectionModel().getSelectedItem();

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/projectScene/ProjectScene.fxml"));
                Parent root = (Parent) loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/modena.css");
                Stage stage = new Stage();
                stage.setTitle("RETAM - Proyecto: " + project.getName());
                stage.setScene(scene);

                stage.setMinWidth(650);
                stage.setMinHeight(550);

                ProjectSceneController ctrl = loader.getController();
                ctrl.initData(project);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProjectTableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void onActionBackButton(ActionEvent event) {
        parentCtrl.popPane();
    }
}
