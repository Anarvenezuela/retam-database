/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers;

import fundabitat.retam.models.Project;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ProjectInfoController implements Initializable {

    @FXML
    private Label codeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label countryLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Project p) {
        codeLabel.setText(p.getCode());
        nameLabel.setText(p.getName());
        countryLabel.setText(p.getCountryName());
    }

}
