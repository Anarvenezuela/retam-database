/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.Descriptor;
import fundabitat.retam.models.Organization;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.Representative;
import fundabitat.retam.models.SubDescriptor;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ProjectInfoController implements Initializable, ProjectSceneInfoController {

    @FXML
    private Label projectCodeLabel;
    @FXML
    private Label projectNameLabel;
    @FXML
    private Label projectCountryLabel;
    @FXML
    private Label projectDurationLabel;
    @FXML
    private Label projectStartingDateLabel;

    @FXML
    private Label organizationCodeLabel;
    @FXML
    private Label organizationNameLabel;
    @FXML
    private Label organizationCountryLabel;
    @FXML
    private Label organizationAddressLabel;
    @FXML
    private Label organizationCityLabel;
    @FXML
    private Label organizationPhone1Label;
    @FXML
    private Label organizationPhone2Label;
    @FXML
    private Label organizationFax1Label;
    @FXML
    private Label organizationFax2Label;
    @FXML
    private Label organizationEmailLabel;
    @FXML
    private Label organizationWebsiteLabel;
    @FXML
    private Label organizationPostalCodeLabel;

    @FXML
    private Label representativeNameLabel;
    @FXML
    private Label representativeProfessionLabel;
    @FXML
    private Label representativePositionLabel;

    @FXML
    private Accordion descriptorsAccordion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initData(Project p) {
        initProyectoInfo(p);
        initOrganization(p.getIdRepresentative().getIdOrganization());
        initRepresentative(p.getIdRepresentative());
        addTitledPanesToAccordion(p);
    }

    private void initProyectoInfo(Project p) {
        projectCodeLabel.setText(p.getCode());
        projectNameLabel.setText(p.getName());
        projectCountryLabel.setText(p.getCountryName());
        projectDurationLabel.setText(p.getDuration());
        projectStartingDateLabel.setText(p.getStartingDate());
    }

    private void initOrganization(Organization o) {
        organizationCodeLabel.setText(o.getCode().toString());
        organizationNameLabel.setText(o.getName());
        organizationAddressLabel.setText(o.getAddress());
        organizationCityLabel.setText(o.getCity());
        organizationPhone1Label.setText(o.getPhone1());
        organizationPhone2Label.setText(o.getPhone2());
        organizationFax1Label.setText(o.getFax1());
        organizationFax2Label.setText(o.getFax2());
        organizationWebsiteLabel.setText(o.getWebsite());
        organizationPostalCodeLabel.setText(o.getPostalCode());
        organizationCountryLabel.setText(o.getCountryName());
        organizationEmailLabel.setText(o.getEmail());
    }

    private void initRepresentative(Representative r) {
        representativeNameLabel.setText(r.getName());
        representativeProfessionLabel.setText(r.getProfession());
        representativePositionLabel.setText(r.getPosition());
    }

    private void addTitledPanesToAccordion(Project p) {

        Collection<Descriptor> descriptors = p.getDescriptorCollection();
        Collection<SubDescriptor> subdescriptors = p.getSubDescriptorCollection();

        for (Descriptor descriptor : descriptors) {
            TitledPane tp = new TitledPane();
            tp.setText(descriptor.getName());

            ObservableList<String> items = FXCollections.observableArrayList();

            for (SubDescriptor sub : subdescriptors) {

                if (sub.getIdDescriptor().equals(descriptor)) {
                    items.add(sub.getName());
                }
            }

            ListView<String> listView = new ListView(items);

            tp.setContent(listView);
            descriptorsAccordion.getPanes().add(tp);
        }
    }

}
