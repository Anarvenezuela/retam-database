/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.controllers.interfaces.ChildrenControllerInterface;
import fundabitat.retam.controllers.interfaces.ParentControllerInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class HomeController implements Initializable, ChildrenControllerInterface {

    private ParentControllerInterface parent;

    @FXML
    private ImageView banner;

    @FXML
    private AnchorPane container;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        banner.fitWidthProperty().bind(container.widthProperty());

        container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                System.out.println("t: " + t + "t1: " + t1);
            }
        });
    }

    @FXML
    private void onProjectClick(ActionEvent event) {
    }

    @FXML
    private void onOrganizationClick(ActionEvent event) {
    }

    @Override
    public void addParentController(ParentControllerInterface ctrl) {
        parent = ctrl;
    }

}
