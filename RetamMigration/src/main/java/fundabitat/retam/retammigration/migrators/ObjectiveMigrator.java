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
import fundabitat.retam.models.ProjectPopulation;
import fundabitat.retam.retammigration.oldmodels.Objetivos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.javatuples.Pair;

/**
 *
 * @author marcos
 */
public class ObjectiveMigrator extends AbstractMigrator<Objetivos> {

    /**
     * These constants are defined by the old app at the application level. They
     * are not in the database.
     */
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

    /**
     * We are spliting the old 'objetivos' table into 8 different tables (3
     * different relations).
     */
    @Override
    protected void process(List<Objetivos> objectives) {

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        addProjectInfo(objectives, projects);
        addInitiatives(objectives, projects);
        addPopulationParticipation(objectives, projects);
        addBeneficiaryPopulation(objectives, projects);
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Objetivos> list = read(Objetivos.class);
        write(list);
    }

    /**
     * Complements the project table with info found in the old 'objetivos'
     * table.
     */
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

    /**
     * Adds information in the initiatives table.
     */
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

    /**
     * Adds information in the population participation tables.
     */
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

    /**
     * Adds information in the project population tables.
     */
    private void addBeneficiaryPopulation(List<Objetivos> objectives, List<Project> projects) {

        List<PopulationType> popTypes = seedPopulationTypes();
        List<PopulationSegment> popSegments = seedPopulationSegments();

        for (Objetivos o : objectives) {

            List<Pair<String, String>> benefPopulationToAdd
                    = checkBeneficiaryPopulation(o);

            for (Pair<String, String> benefPopulationPair : benefPopulationToAdd) {

                PopulationSegment popSegment = getPopulationSegmentByName(
                        popSegments, benefPopulationPair.getValue0());
                PopulationType popType = getPopulationTypeByName(
                        popTypes, benefPopulationPair.getValue1());
                Project project = getProjectByCode(projects, o.getCodigo());

                ProjectPopulation proPopulation
                        = new ProjectPopulation(project, popSegment, popType);

                em.persist(proPopulation);
            }

        }
    }

    private List<Pair<String, String>> checkBeneficiaryPopulation(Objetivos o) {

        List<Pair<String, String>> benefPopulationToAdd
                = new ArrayList();

        Pair<String, String> pair;

        if (o.isNinosU()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[0], POPULATION_TYPE_NAMES[0]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isNinosR()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[0], POPULATION_TYPE_NAMES[1]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isNinosI()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[0], POPULATION_TYPE_NAMES[2]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isMujeresU()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[1], POPULATION_TYPE_NAMES[0]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isMujeresR()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[1], POPULATION_TYPE_NAMES[1]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isMujeresI()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[1], POPULATION_TYPE_NAMES[2]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isOtrosSU()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[2], POPULATION_TYPE_NAMES[0]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isOtrosSR()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[2], POPULATION_TYPE_NAMES[1]);
            benefPopulationToAdd.add(pair);
        }

        if (o.isOtrosSI()) {
            pair = new Pair(POPULATION_SEGMENT_NAMES[2], POPULATION_TYPE_NAMES[2]);
            benefPopulationToAdd.add(pair);
        }

        return benefPopulationToAdd;
    }

    private PopulationParticipationType getPopulationParticipationByName(List<PopulationParticipationType> popPartTypes, String populationPartStr) {
        return popPartTypes.stream()
                .filter(i -> i.getName().equals(populationPartStr))
                .findFirst().get();
    }

    private PopulationType getPopulationTypeByName(List<PopulationType> popTypes, String populationTypeStr) {
        return popTypes.stream()
                .filter(i -> i.getName().equals(populationTypeStr))
                .findFirst().get();
    }

    private PopulationSegment getPopulationSegmentByName(List<PopulationSegment> popSegments, String populationSegmentStr) {
        return popSegments.stream()
                .filter(i -> i.getName().equals(populationSegmentStr))
                .findFirst().get();
    }

    /**
     * The seed methods add information in the database that was implicit in the
     * app, but wans't stored in the database.
     */
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
