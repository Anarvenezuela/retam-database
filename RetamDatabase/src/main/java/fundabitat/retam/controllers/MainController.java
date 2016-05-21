package fundabitat.retam.controllers;

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

public class MainController implements Initializable {

    @FXML
    private AnchorPane content;

    private AnchorPane projectPane;
    private AnchorPane organizationPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadPanes();
    }

    @FXML
    public void onActionProjectButton(ActionEvent event) {
        content.getChildren().setAll(projectPane);
    }

    @FXML
    public void onActionOrganizationButton(ActionEvent event) {
        content.getChildren().setAll(organizationPane);
    }

    private void loadPanes() {
        try {
            projectPane = FXMLLoader.load(getClass().getResource("/fxml/ProjectTable.fxml"));
            organizationPane = FXMLLoader.load(getClass().getResource("/fxml/OrganizationTable.fxml"));
        } catch (IOException ex) {
            // TODO
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
