/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.Country;
import fundabitat.retam.models.Exchange;
import fundabitat.retam.models.Project;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ExchangeInfoController implements Initializable, ProjectSceneInfoController {

    // These should get refactored somewhere
    // Eventually these should take into account the language of the app.
    private static final String yes = "Sí";
    private static final String no = "No";

    @FXML
    private Label knownProjectsLabel;
    @FXML
    private Label projectNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label faxLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label websiteLabel;
    @FXML
    private Label exchangeTypeLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label organizationLabel;
    @FXML
    private Label representativeLabel;
    @FXML
    private Label ContactExistsLabel;
    @FXML
    private Label noContactReasonLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initData(Project p) {

        Collection<Exchange> exchanges = p.getExchangeCollection();
        setKnownProject(exchanges);

        Exchange exchange = fillExchange(exchanges);

        projectNameLabel.setText(exchange.getProjectName());
        addressLabel.setText(exchange.getAddress());
        addressLabel.setText(exchange.getAddress());
        cityLabel.setText(exchange.getCity());
        phoneLabel.setText(exchange.getPhone());
        faxLabel.setText(exchange.getFax());
        emailLabel.setText(exchange.getEmail());
        websiteLabel.setText(exchange.getWebpage());
        exchangeTypeLabel.setText(exchange.getExchangeType());
        countryLabel.setText(exchange.getIdCountry().getName());
        organizationLabel.setText(exchange.getOrganization());
        representativeLabel.setText(exchange.getRepresentative());
        ContactExistsLabel.setText(setYesNo(exchange.getContactExists()));
        noContactReasonLabel.setText(exchange.getNoContactReason());

    }

    /**
     * The current project might not have an associated exchange. Thus there is
     * no known project.
     */
    private void setKnownProject(Collection<Exchange> exchanges) {

        knownProjectsLabel.setText(setYesNo(!exchanges.isEmpty()));
    }

    /**
     * The associated exchange might not exists, we create an empty one just to
     * fill the data with blanks.
     */
    private Exchange fillExchange(Collection<Exchange> exchanges) {

        Exchange exchange;

        if (exchanges.isEmpty()) {
            exchange = new Exchange();
            Country country = new Country();
            exchange.setIdCountry(country);
        } else {
            exchange = exchanges.iterator().next();
        }

        return exchange;
    }

    /**
     * This might need to be refactored to somewhere else.
     */
    private String setYesNo(boolean expression) {

        String res;

        if (expression) {
            res = yes;
        } else {
            res = no;
        }

        return res;
    }

}
