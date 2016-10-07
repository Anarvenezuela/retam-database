/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.controllers.interfaces.ProjectSceneInfoController;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.ProjectStaff;
import fundabitat.retam.models.StaffJobType;
import fundabitat.retam.persistence.PersistenceManager;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class HumanResourcesInfoController implements Initializable, ProjectSceneInfoController {

    private static final String[] FOREIGN_LABELS = {"Nacional", "Extranjero"};
    private static final String[] VOLUNTEER_LABELS = {"Remunerado", "Voluntario"};
    private static final String TOTAL_LABEL = "Total";
    private static final String FAKE_TAB = "   ";

    private static final int FIRST_NATIONALITY_ROW = 2;
    private static final int TABLE_ROWS = 5;
    private static final int FIRST_VOLUNTEER_COLUMN = 1;
    private static final int VOLUNTEER_ROW = 0;
    private static int TABLE_COLUMNS;
    private static final int JOB_TYPE_ROW = 1;
    private static final int FOREING_ROW = 3;
    private static final int NATIVE_ROW = 2;

    @FXML
    private GridPane staffGridPane;
    @FXML
    private Label amazonResidentsLabel;
    @FXML
    private Label benefStaffLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void initData(Project p) {
        amazonResidentsLabel.setText(p.getStaffLivingInAmazon());
        benefStaffLabel.setText(p.getStaffPartOfCommunity());

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query findAllJobtypes = eManager.createNamedQuery("StaffJobType.findAll");
        List<StaffJobType> jobTypes = findAllJobtypes.getResultList();

        makeGridRows();
        makeGridColumns(jobTypes);
        fillGrid(p, jobTypes);

    }

    private void makeGridRows() {

        for (int i = 0; i < FOREIGN_LABELS.length; ++i) {
            staffGridPane.add(new Label(FAKE_TAB + FOREIGN_LABELS[i]), 0,
                    i + FIRST_NATIONALITY_ROW);
        }

        staffGridPane.add(new Label(FAKE_TAB + TOTAL_LABEL), 0,
                FOREIGN_LABELS.length + FIRST_NATIONALITY_ROW);

        int percentageRow = 100 / TABLE_ROWS;

        // The first row already had a constraint in the corresponding fxml
        for (int i = 0; i < TABLE_ROWS - 1; ++i) {

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(percentageRow);
            staffGridPane.getRowConstraints().add(row);
        }
    }

    private void makeGridColumns(List<StaffJobType> jobTypes) {

        for (int i = 0; i < VOLUNTEER_LABELS.length; ++i) {
            staffGridPane.add(new Label(FAKE_TAB + VOLUNTEER_LABELS[i]),
                    i * jobTypes.size() + FIRST_VOLUNTEER_COLUMN,
                    VOLUNTEER_ROW, jobTypes.size(), 1);
        }

        staffGridPane.add(new Label(FAKE_TAB + TOTAL_LABEL),
                VOLUNTEER_LABELS.length * jobTypes.size() + FIRST_VOLUNTEER_COLUMN, 0);

        TABLE_COLUMNS = FIRST_VOLUNTEER_COLUMN
                + VOLUNTEER_LABELS.length * jobTypes.size() + 1;

        int percentageCol = 100 / (TABLE_COLUMNS);

        // The first column already had a constraint in the corresponding fxml
        for (int i = 0; i < TABLE_COLUMNS - 1; ++i) {

            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(percentageCol);
            staffGridPane.getColumnConstraints().add(col);
        }

        for (int i = 0; i < VOLUNTEER_LABELS.length * jobTypes.size(); ++i) {
            String jobName = jobTypes.get(i % jobTypes.size()).getName();
            staffGridPane.add(new Label(FAKE_TAB + jobName),
                    i + FIRST_VOLUNTEER_COLUMN, JOB_TYPE_ROW);
        }
    }

    private void fillGrid(Project p, List<StaffJobType> jobTypes) {
        Collection<ProjectStaff> projectStaff = p.getProjectStaffCollection();

        int foreignTotal = 0;
        int nationalTotal = 0;
        int[] notVolunteerTotal = new int[jobTypes.size()];
        int[] volunteerTotal = new int[jobTypes.size()];

        for (ProjectStaff ps : projectStaff) {

            int index = jobTypes.indexOf(ps.getIdStaffJobType());

            if (ps.getIsForeign()) {

                foreignTotal += ps.getQuantity();

                if (ps.getIsVolunteer()) {
                    volunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label(FAKE_TAB + ps.getQuantity()),
                            jobTypes.size() + index + FIRST_VOLUNTEER_COLUMN,
                            FOREING_ROW);
                } else {
                    notVolunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label(FAKE_TAB + ps.getQuantity()),
                            index + FIRST_VOLUNTEER_COLUMN, FOREING_ROW);
                }

            } else {
                nationalTotal += ps.getQuantity();

                if (ps.getIsVolunteer()) {
                    volunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label(FAKE_TAB + ps.getQuantity()),
                            jobTypes.size() + index + FIRST_VOLUNTEER_COLUMN,
                            NATIVE_ROW);
                } else {
                    notVolunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label(FAKE_TAB + ps.getQuantity()),
                            index + FIRST_VOLUNTEER_COLUMN, NATIVE_ROW);
                }
            }
        }

        for (int i = 0; i < notVolunteerTotal.length; ++i) {
            staffGridPane.add(new Label(FAKE_TAB + notVolunteerTotal[i]),
                    i + FIRST_VOLUNTEER_COLUMN, TABLE_ROWS - 1);
        }

        for (int i = 0; i < volunteerTotal.length; ++i) {
            staffGridPane.add(new Label(FAKE_TAB + volunteerTotal[i]),
                    i + FIRST_VOLUNTEER_COLUMN + notVolunteerTotal.length,
                    TABLE_ROWS - 1);
        }

        staffGridPane.add(new Label(FAKE_TAB + nationalTotal), TABLE_COLUMNS - 1,
                NATIVE_ROW);

        staffGridPane.add(new Label(FAKE_TAB + foreignTotal), TABLE_COLUMNS - 1,
                FOREING_ROW);
    }

}
