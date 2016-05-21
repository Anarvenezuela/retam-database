/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.models.Project;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ProjectSceneController implements Initializable {

    @FXML
    private AnchorPane content;

    private Project project;

    private FXMLLoader projectInfoLoader;
    private AnchorPane projectInfoPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(Project p) {
        project = p;
        loadPanes();

        content.getChildren().setAll(projectInfoPane);

    }

    private void loadPanes() {

        projectInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/ProjectInfo.fxml"));
        try {
            projectInfoPane = (AnchorPane) projectInfoLoader.load();
            ProjectInfoController projectInfoCtrl = projectInfoLoader.getController();
            projectInfoCtrl.initData(project);
        } catch (IOException ex) {
            Logger.getLogger(ProjectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
