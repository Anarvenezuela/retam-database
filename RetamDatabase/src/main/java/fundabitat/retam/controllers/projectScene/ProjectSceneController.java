/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.Project;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private FXMLLoader partInfoLoader;
    private AnchorPane partInfoPane;

    private FXMLLoader objectivesInfoLoader;
    private AnchorPane objectivesInfoPane;

    private FXMLLoader humanResourcesInfoLoader;
    private AnchorPane humanResourcesInfoPane;

    private FXMLLoader exchangeInfoLoader;
    private AnchorPane exchangeInfoPane;

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

        partInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/ParticipationInfo.fxml"));

        objectivesInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/ObjectivesInfo.fxml"));

        humanResourcesInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/HumanResourcesInfo.fxml"));

        exchangeInfoLoader = new FXMLLoader(getClass()
                .getResource("/fxml/projectScene/ExchangeInfo.fxml"));

        List<FXMLLoader> loaders = new ArrayList();
        loaders.add(projectInfoLoader);
        loaders.add(orgsInfoLoader);
        loaders.add(benefInfoLoader);
        loaders.add(partInfoLoader);
        loaders.add(objectivesInfoLoader);
        loaders.add(humanResourcesInfoLoader);
        loaders.add(exchangeInfoLoader);

        try {

            projectInfoPane = (AnchorPane) projectInfoLoader.load();
            orgsInfoPane = (AnchorPane) orgsInfoLoader.load();
            benefInfoPane = (AnchorPane) benefInfoLoader.load();
            partInfoPane = (AnchorPane) partInfoLoader.load();
            objectivesInfoPane = (AnchorPane) objectivesInfoLoader.load();
            humanResourcesInfoPane = (AnchorPane) humanResourcesInfoLoader.load();
            exchangeInfoPane = (AnchorPane) exchangeInfoLoader.load();

            for (FXMLLoader loader : loaders) {
                ProjectSceneInfoController ctrl = loader.getController();
                ctrl.initData(project);
            }

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

    @FXML
    public void onActionParticipationButton(ActionEvent event) {
        content.getChildren().setAll(partInfoPane);
    }

    @FXML
    public void onActionObjectiveButton(ActionEvent event) {
        content.getChildren().setAll(objectivesInfoPane);
    }

    @FXML
    public void onActionHumaResourcesButton(ActionEvent event) {
        content.getChildren().setAll(humanResourcesInfoPane);
    }

    @FXML
    public void onActionExchangeButton(ActionEvent event) {
        content.getChildren().setAll(exchangeInfoPane);
    }

}
