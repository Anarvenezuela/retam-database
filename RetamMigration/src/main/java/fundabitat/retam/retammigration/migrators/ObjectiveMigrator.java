/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.InitiativeType;
import fundabitat.retam.models.PopulationParticipationType;
import fundabitat.retam.models.PopulationSegment;
import fundabitat.retam.models.PopulationType;
import fundabitat.retam.models.Project;
import fundabitat.retam.retammigration.oldmodels.Objetivos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class ObjectiveMigrator extends AbstractMigrator<Objetivos> {

    private static final String[] INITIATIVE_TYPE_NAMES = {
        "Institución Ejecutora", "Comnidad Beneficiada", "ONG",
        "Agencia Financiadora", "Instituciñón Gubernamental",
        "Institución Religiosa"
    };

    private static final String[] POPULATION_PARTICIPATION_TYPE_NAMES = {
        "Concepción", "Planificación", "Implementación", "Difusión",
        "Capacitación", "Seguimiento"
    };

    private static final String[] POPULATION_TYPE_NAMES = {
        "Urbana", "Rural", "Indígena"
    };

    private static final String[] POPULATION_SEGMENT_NAMES = {
        "Niños y adolescentes", "Mujeres y hombres", "Otros segmentos"
    };

    /**
     * There were some new lines in the data of the database. We replaced them
     * with € because it was casing problems with csv export. We revert the
     * change here.
     */
    private static final String NEW_LINE_PLACEHOLDER = "€";

    public ObjectiveMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Objetivos> objectives) {

        List<PopulationType> popTypes = seedPopulationTypes();
        List<PopulationSegment> popSegments = seedPopulationSegments();

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        addProjectInfo(objectives, projects);
        addInitiatives(objectives, projects);
        addPopulationParticipation(objectives, projects);
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Objetivos> list = read(Objetivos.class);
        write(list);
    }

    private void addProjectInfo(List<Objetivos> objectives, List<Project> projects) {

        for (Objetivos o : objectives) {

            String projectCode = o.getCodigo();
            Project project = getProjectByCode(projects, projectCode);

            project.setGeneralObjective(replaceWithNewLine(o.getObjetivoGeneral()));
            project.setSpecificObjective(replaceWithNewLine(o.getObjetivosEspecificos()));
            project.setMethodology(replaceWithNewLine(o.getMetodologia()));
            project.setDuration(o.getDuracion());
            project.setStartingDate(o.getFechaInicio());
            em.persist(project);
        }
    }

    private void addInitiatives(List<Objetivos> objectives, List<Project> projects) {

        List<InitiativeType> initTypes = seedInititaiveTypes();

        for (Objetivos o : objectives) {

            String projectCode = o.getCodigo();
            Project project = getProjectByCode(projects, projectCode);

            List<String> initiativesToAdd = checkInitiatives(o);

            for (String initiativeStr : initiativesToAdd) {
                InitiativeType initType = getInitiativeByName(initTypes, initiativeStr);
                project.getInitiativeTypeCollection().add(initType);
                em.persist(project);
            }
        }
    }

    private InitiativeType getInitiativeByName(List<InitiativeType> initTypes, String initiativeStr) {
        return initTypes.stream()
                .filter(i -> i.getName().equals(initiativeStr))
                .findFirst().get();
    }

    private List<String> checkInitiatives(Objetivos o) {

        List<String> initiativesToAdd = new ArrayList();

        if (o.isIE()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[0]);
        }

        if (o.isCBeneficiada()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[1]);
        }

        if (o.isONG()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[2]);
        }

        if (o.isAFinanciadora()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[3]);
        }

        if (o.isIGubernamental()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[4]);
        }

        if (o.isOReligiosa()) {
            initiativesToAdd.add(INITIATIVE_TYPE_NAMES[5]);
        }

        return initiativesToAdd;
    }

    private List<InitiativeType> seedInititaiveTypes() {

        List<InitiativeType> list = new ArrayList();

        for (String initiativeName : INITIATIVE_TYPE_NAMES) {
            InitiativeType type = new InitiativeType(initiativeName);
            list.add(type);
            em.persist(type);
        }

        return list;
    }

    private void addPopulationParticipation(List<Objetivos> objectives, List<Project> projects) {

        List<PopulationParticipationType> popPartTypes = seedPopulationParticipationTypes();

        for (Objetivos o : objectives) {

            String projectCode = o.getCodigo();
            Project project = getProjectByCode(projects, projectCode);

            List<String> populationParticipationToAdd = checkPopulationPArticipation(o);

            for (String populationPartType : populationParticipationToAdd) {
                PopulationParticipationType popPartType = getPopulationParticipationByName(popPartTypes, populationPartType);
                project.getPopulationParticipationTypeCollection().add(popPartType);
                em.persist(project);
            }

        }
    }

    private List<String> checkPopulationPArticipation(Objetivos o) {

        List<String> populationParticipationToAdd = new ArrayList();

        if (o.isConcepcion()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[0]);
        }

        if (o.isPlanificacion()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[1]);
        }

        if (o.isImplementacion()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[2]);
        }

        if (o.isDifusion()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[3]);
        }

        if (o.isCapacitacion()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[4]);
        }

        if (o.isSeguimiento()) {
            populationParticipationToAdd.add(POPULATION_PARTICIPATION_TYPE_NAMES[5]);
        }

        return populationParticipationToAdd;
    }

    private PopulationParticipationType getPopulationParticipationByName(List<PopulationParticipationType> popPartTypes, String populationPartStr) {
        return popPartTypes.stream()
                .filter(i -> i.getName().equals(populationPartStr))
                .findFirst().get();
    }

    private List<PopulationParticipationType> seedPopulationParticipationTypes() {

        List<PopulationParticipationType> list = new ArrayList();

        for (String participationName : POPULATION_PARTICIPATION_TYPE_NAMES) {
            PopulationParticipationType type = new PopulationParticipationType(participationName);
            list.add(type);
            em.persist(type);
        }

        return list;
    }

    private List<PopulationType> seedPopulationTypes() {

        List<PopulationType> list = new ArrayList();

        for (String populationName : POPULATION_TYPE_NAMES) {
            PopulationType type = new PopulationType(populationName);
            list.add(type);
            em.persist(type);
        }

        return list;
    }

    private List<PopulationSegment> seedPopulationSegments() {

        List<PopulationSegment> list = new ArrayList();

        for (String populationName : POPULATION_SEGMENT_NAMES) {
            PopulationSegment type = new PopulationSegment(populationName);
            list.add(type);
            em.persist(type);
        }

        return list;
    }

    private String replaceWithNewLine(String s) {

        if (s != null) {
            s = s.replaceAll(NEW_LINE_PLACEHOLDER, System.lineSeparator());
        }

        return s;
    }

    private Project getProjectByCode(List<Project> projects, String projectCode) {
        return projects.stream().
                filter(p -> p.getCode().equals(projectCode)).
                findFirst().get();
    }

}
