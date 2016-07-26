/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.controllers.projectScene;

import fundabitat.retam.models.Organization;
import fundabitat.retam.models.Participation;
import fundabitat.retam.models.ParticipationType;
import fundabitat.retam.models.Project;
import fundabitat.retam.persistence.PersistenceManager;
import fundabitat.retam.utils.Function;
import fundabitat.retam.utils.ListViewCellUtil;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author marcos
 */
public class ParticipationInfoController implements Initializable {

    @FXML
    private TableView<Organization> organizationTable;
    @FXML
    private TableColumn<Organization, String> organizationCode;
    @FXML
    private TableColumn<Organization, String> organizationName;
    @FXML
    private TableColumn<Organization, String> organizationCountry;

    @FXML
    private ListView<ParticipationType> participationTypes;

    private Project project;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(Project p) {

        project = p;

        PersistenceManager pManager = PersistenceManager.getInstance();
        EntityManager eManager = pManager.getEntityManagerFactory().createEntityManager();

        Query findAllCountries;

        findAllCountries = eManager.createNamedQuery("Project.getParticipantOrgs")
                .setParameter("projectId", p.getIdProject());

        List<Organization> orgs = findAllCountries.getResultList();

        eManager.close();

        organizationCode.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("code"));
        organizationName.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("name"));
        organizationCountry.setCellValueFactory(
                new PropertyValueFactory<Organization, String>("countryName"));

        final ObservableList<Organization> observableList = FXCollections.observableArrayList(orgs);
        organizationTable.setItems(observableList);
    }

    @FXML
    public void onClickOrg(MouseEvent event) {

        Organization org = organizationTable.getSelectionModel().getSelectedItem();
        Collection<Participation> participations = project.getParticipationCollection();
        List<ParticipationType> types = Participation.getPartTypeByOrgFromCollection(participations, org);

        ObservableList<ParticipationType> observableTypeList;
        observableTypeList = FXCollections.observableArrayList(types);
        participationTypes.setItems(observableTypeList);

        ListViewCellUtil.setupCell(participationTypes, new Function<ParticipationType, String>() {
            @Override
            public String apply(ParticipationType input) {
                return input.getName();
            }

        });

    }

}
