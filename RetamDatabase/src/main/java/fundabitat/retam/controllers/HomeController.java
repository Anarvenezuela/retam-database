/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.controllers.interfaces.ChildrenControllerInterface;
import fundabitat.retam.controllers.interfaces.ParentControllerInterface;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class HomeController implements Initializable, ChildrenControllerInterface {

    private static final float BANNER_HEIGHT = 320;
    private static final float BANNER_HEIGHT_WIDTH_RATIO = 2.8f;
    private static final String BANNER_BACKGROUND = "-fx-background-color: #FFFFFF";

    private ParentControllerInterface parent;

    @FXML
    private ImageView banner;

    @FXML
    private AnchorPane container;

    @FXML
    private AnchorPane menuContainer;

    @FXML
    private HBox bannerBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        banner.fitWidthProperty().bind(container.widthProperty());
        resizeMenu();
        bannerBox.setStyle(BANNER_BACKGROUND);
    }

    /**
     * Resizes the menu depending on the size of the banner. The banner resizes
     * to a max height of BANNER_HEIGHT, and since the banner preserves the
     * aspect ratio (calculated manually on BANNER_HEIGHT_WIDTH_RATIO) we can
     * use it to calculate the height of the banner.
     */
    private void resizeMenu() {
        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                AnchorPane.setTopAnchor(menuContainer,
                        Math.min(t1.doubleValue() / BANNER_HEIGHT_WIDTH_RATIO,
                                BANNER_HEIGHT));
            }
        });
    }

    @FXML
    private void onProjectClick(ActionEvent event) {

        FXMLLoader projectHomeLoader = new FXMLLoader(getClass()
                .getResource("/fxml/ProjectHome.fxml"));

        try {
            AnchorPane projectHomePane = (AnchorPane) projectHomeLoader.load();
            ProjectHomeController projectHomeCtrl = projectHomeLoader.getController();
            projectHomeCtrl.addParentController(parent);
            parent.pushPane(projectHomePane);

        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onOrganizationClick(ActionEvent event) {
        FXMLLoader orgHomeLoader = new FXMLLoader(getClass()
                .getResource("/fxml/OrganizationTable.fxml"));

        try {
            AnchorPane orgHomePane = (AnchorPane) orgHomeLoader.load();
            OrganizationTableController organizationTableCtrl = orgHomeLoader.getController();
            organizationTableCtrl.addParentController(parent);
            parent.pushPane(orgHomePane);

        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addParentController(ParentControllerInterface ctrl) {
        parent = ctrl;
    }

}
