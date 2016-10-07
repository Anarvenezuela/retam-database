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

        staffGridPane.add(new Label(""), 0, 1);

        for (int i = 0; i < FOREIGN_LABELS.length; ++i) {
            staffGridPane.add(new Label("   " + FOREIGN_LABELS[i]), 0, i + 2);
        }

        staffGridPane.add(new Label("   " + TOTAL_LABEL), 0, FOREIGN_LABELS.length + 2);

        int percentageRow = 100 / (3 + FOREIGN_LABELS.length);

        for (int i = 0; i <= FOREIGN_LABELS.length + 1; ++i) {

            RowConstraints row = new RowConstraints();
            row.setPercentHeight(percentageRow);
            staffGridPane.getRowConstraints().add(row);
        }
    }

    private void makeGridColumns(List<StaffJobType> jobTypes) {
        for (int i = 0; i < VOLUNTEER_LABELS.length; ++i) {
            staffGridPane.add(new Label("   " + VOLUNTEER_LABELS[i]), i * jobTypes.size() + 1, 0, jobTypes.size(), 1);
        }

        staffGridPane.add(new Label("   " + TOTAL_LABEL), VOLUNTEER_LABELS.length * jobTypes.size() + 1, 0);

        int percentageCol = 100 / (2 + VOLUNTEER_LABELS.length * jobTypes.size());

        for (int i = 0; i <= VOLUNTEER_LABELS.length * jobTypes.size(); ++i) {

            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(percentageCol);
            staffGridPane.getColumnConstraints().add(col);
        }

        for (int i = 0; i < VOLUNTEER_LABELS.length * jobTypes.size(); ++i) {
            staffGridPane.add(new Label("   " + jobTypes.get(i % jobTypes.size()).getName()), i + 1, 1);
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
                    staffGridPane.add(new Label("   " + ps.getQuantity()), jobTypes.size() + index + 1, 3);
                } else {
                    notVolunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label("   " + ps.getQuantity()), index + 1, 3);
                }

            } else {
                nationalTotal += ps.getQuantity();

                if (ps.getIsVolunteer()) {
                    volunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label("   " + ps.getQuantity()), jobTypes.size() + index + 1, 2);
                } else {
                    notVolunteerTotal[index] += ps.getQuantity();
                    staffGridPane.add(new Label("   " + ps.getQuantity()), index + 1, 2);
                }
            }
        }

        for (int i = 0; i < notVolunteerTotal.length; ++i) {
            staffGridPane.add(new Label("   " + notVolunteerTotal[i]), i + 1, 4);
        }

        for (int i = 0; i < volunteerTotal.length; ++i) {
            staffGridPane.add(new Label("   " + volunteerTotal[i]),
                    i + 1 + notVolunteerTotal.length, 4);
        }

        staffGridPane.add(new Label("   " + nationalTotal),
                jobTypes.size() * 2 + 1, 2);

        staffGridPane.add(new Label("   " + foreignTotal),
                jobTypes.size() * 2 + 1, 3);
    }

}
