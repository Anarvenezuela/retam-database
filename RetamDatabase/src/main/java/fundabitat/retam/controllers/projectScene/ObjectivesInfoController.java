/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.InitiativeType;
import fundabitat.retam.models.PopulationParticipationType;
import fundabitat.retam.models.PopulationSegment;
import fundabitat.retam.models.PopulationType;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.ProjectPopulation;
import fundabitat.retam.persistence.PersistenceManager;
import fundabitat.retam.utils.Function;
import fundabitat.retam.utils.ListViewCellUtil;
import fundabitat.retam.utils.ListViewUtil;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ObjectivesInfoController implements Initializable, ProjectSceneInfoController {

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

    @FXML
    private GridPane benefPopulationGrid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initData(Project p) {

        setupObjectives(p);
        setupInitiatives(p);
        setupPopulationParticipation(p);
        setupProjectParticipation(p);
    }

    private void setupProjectParticipation(Project p) {

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query findAllPopSegment = eManager.createNamedQuery("PopulationSegment.findAll");
        List<PopulationSegment> segments = findAllPopSegment.getResultList();

        Query findAllPopType = eManager.createNamedQuery("PopulationType.findAll");
        List<PopulationType> popTypes = findAllPopType.getResultList();

        eManager.close();

        makeProjectParticipationGrid(segments, popTypes);
        fillGrid(p, segments, popTypes);
    }

    private void fillGrid(Project p, List<PopulationSegment> segments, List<PopulationType> popTypes) {

        Collection<ProjectPopulation> projectpops = p.getProjectPopulationCollection();

        for (ProjectPopulation projectpop : projectpops) {
            PopulationSegment seg = projectpop.getIdPopulationSegment();
            PopulationType type = projectpop.getIdPopulationType();

            int segIndex = segments.indexOf(seg);
            int typeIndex = popTypes.indexOf(type);

            benefPopulationGrid.add(
                    new Label("             X"), typeIndex + 1, segIndex + 1);
        }
    }

    /**
     * Creates the grid. Remember that there is segments.size() + 1 rows and
     * popTypes.size() + 1 columns.
     */
    private void makeProjectParticipationGrid(List<PopulationSegment> segments,
            List<PopulationType> popTypes) {

        makeGridCols(popTypes);
        makeGridRows(segments);
    }

    private void makeGridRows(List<PopulationSegment> segments) {

        for (int i = 0; i < segments.size(); ++i) {
            benefPopulationGrid.add(new Label("   " + segments.get(i).getName()), 0, i + 1);
        }

        int percentageRow = 100 / (1 + segments.size());

        for (int i = 0; i <= segments.size(); ++i) {

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(percentageRow);
            benefPopulationGrid.getRowConstraints().add(row);
        }
    }

    private void makeGridCols(List<PopulationType> popTypes) {

        for (int i = 0; i < popTypes.size(); ++i) {
            benefPopulationGrid.add(new Label("   " + popTypes.get(i).getName()), i + 1, 0);
        }

        int percentageCol = 100 / (1 + popTypes.size());

        for (int i = 0; i <= popTypes.size(); ++i) {

            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(percentageCol);
            benefPopulationGrid.getColumnConstraints().add(col);
        }
    }

    private void setupPopulationParticipation(Project p) {

        Collection<PopulationParticipationType> partTypes
                = p.getPopulationParticipationTypeCollection();

        ListViewUtil.setItems(populationPartListView, partTypes);

        ListViewCellUtil.setupCell(populationPartListView, new Function<PopulationParticipationType, String>() {
            @Override
            public String apply(PopulationParticipationType input) {
                return input.getName();
            }
        });
    }

    private void setupInitiatives(Project p) {

        Collection<InitiativeType> initiatives
                = p.getInitiativeTypeCollection();

        ListViewUtil.setItems(initiativesListView, initiatives);

        ListViewCellUtil.setupCell(initiativesListView, new Function<InitiativeType, String>() {
            @Override
            public String apply(InitiativeType input) {
                return input.getName();
            }
        });
    }

    private void setupObjectives(Project p) {

        generalObjective.setText(p.getGeneralObjective());
        specificObjective.setText(p.getSpecificObjective());
        methodology.setText(p.getMethodology());
    }

}
