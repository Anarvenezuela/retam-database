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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class MainSceneController implements Initializable, ParentControllerInterface {

    @FXML
    private StackPane stackPane;

    private FXMLLoader homeLoader;
    private AnchorPane homePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        homeLoader = new FXMLLoader(getClass()
                .getResource("/fxml/ProjectHome.fxml"));

        try {
            homePane = (AnchorPane) homeLoader.load();
            ChildrenControllerInterface homeCtrl = homeLoader.getController();

            homeCtrl.addParentController(this);

            stackPane.getChildren().add(homePane);
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void pushPane(Node node) {
        ObservableList<Node> children = stackPane.getChildren();

        if (!children.isEmpty()) {
            children.get(children.size() - 1).setVisible(false);
        }

        children.add(node);
    }

    @Override
    public void popPane() {
        ObservableList<Node> children = stackPane.getChildren();
        children.remove(children.size() - 1);

        if (!children.isEmpty()) {
            children.get(children.size() - 1).setVisible(true);
        }
    }

}
