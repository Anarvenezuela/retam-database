/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.models.Project;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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

    private FXMLLoader orgsInfoLoader;
    private AnchorPane orgsInfoPane;

    private FXMLLoader benefInfoLoader;
    private AnchorPane benefInfoPane;

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

        orgsInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/OrganizationsInfo.fxml"));

        benefInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/BeneficiaryInfo.fxml"));

        try {
            projectInfoPane = (AnchorPane) projectInfoLoader.load();
            ProjectInfoController projectInfoCtrl = projectInfoLoader.getController();
            projectInfoCtrl.initData(project);

            orgsInfoPane = (AnchorPane) orgsInfoLoader.load();
            OrganizationsInfoController orgInfoCtrl = orgsInfoLoader.getController();
            orgInfoCtrl.initData(project);

            benefInfoPane = (AnchorPane) benefInfoLoader.load();
            BeneficiaryInfoController benefInfoCtrl = benefInfoLoader.getController();
            benefInfoCtrl.initData(project);

        } catch (IOException ex) {
            Logger.getLogger(ProjectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void onActionProjectButton(ActionEvent event) {
        content.getChildren().setAll(projectInfoPane);
    }

    @FXML
    public void onActionOrganizationButton(ActionEvent event) {
        content.getChildren().setAll(orgsInfoPane);
    }

    @FXML
    public void onActionBeneficiaryButton(ActionEvent event) {
        content.getChildren().setAll(benefInfoPane);
    }

}
