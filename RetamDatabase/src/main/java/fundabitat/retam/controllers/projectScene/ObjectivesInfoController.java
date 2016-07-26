/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.models.InitiativeType;
import fundabitat.retam.models.PopulationParticipationType;
import fundabitat.retam.models.Project;
import fundabitat.retam.utils.Function;
import fundabitat.retam.utils.ListViewCellUtil;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ObjectivesInfoController implements Initializable {

    @FXML
    private Label generalObjective;

    @FXML
    private Label specificObjective;

    @FXML
    private Label methodology;

    @FXML
    private ListView<InitiativeType> initiativesListView;

    @FXML
    private ListView<PopulationParticipationType> populationPartListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initData(Project p) {
        generalObjective.setText(p.getGeneralObjective());
        specificObjective.setText(p.getSpecificObjective());
        methodology.setText(p.getMethodology());

        Collection<InitiativeType> initiatives
                = p.getInitiativeTypeCollection();
        Collection<PopulationParticipationType> partTypes
                = p.getPopulationParticipationTypeCollection();

        ObservableList<InitiativeType> observableInitTypeList;
        observableInitTypeList = FXCollections.observableArrayList(initiatives);
        initiativesListView.setItems(observableInitTypeList);

        ListViewCellUtil.setupCell(initiativesListView, new Function<InitiativeType, String>() {
            @Override
            public String apply(InitiativeType input) {
                return input.getName();
            }
        });

        ObservableList<PopulationParticipationType> observablepopPartList;
        observablepopPartList = FXCollections.observableArrayList(partTypes);
        populationPartListView.setItems(observablepopPartList);

        ListViewCellUtil.setupCell(populationPartListView, new Function<PopulationParticipationType, String>() {
            @Override
            public String apply(PopulationParticipationType input) {
                return input.getName();
            }
        });

    }

}
