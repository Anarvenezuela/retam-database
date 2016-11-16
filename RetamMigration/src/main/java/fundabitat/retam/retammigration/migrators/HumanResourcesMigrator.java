/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Project;
import fundabitat.retam.models.ProjectStaff;
import fundabitat.retam.models.StaffJobType;
import fundabitat.retam.retammigration.oldmodels.RecursosHumanos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class HumanResourcesMigrator extends AbstractMigrator<RecursosHumanos> {

    private static final String[] JOB_TYPES = {"Técnico", "Administrador", "Otros"};

    public HumanResourcesMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<RecursosHumanos> elements) {

        Query findCountries = em.createNamedQuery("Project.findAll");
        List<Project> projects = findCountries.getResultList();
        List<StaffJobType> jobTypes = seedJobTypes();

        for (RecursosHumanos rh : elements) {

            Project project = getProject(rh, projects);

            if (rh.getNaciRemuT() != 0) {
                addProjectStaff(project, jobTypes.get(0), false, false, rh.getNaciRemuT());
            }

            if (rh.getNaciRemuA() != 0) {
                addProjectStaff(project, jobTypes.get(1), false, false, rh.getNaciRemuA());
            }

            if (rh.getNaciRemuO() != 0) {
                addProjectStaff(project, jobTypes.get(2), false, false, rh.getNaciRemuO());
            }

            if (rh.getNaciVolunT() != 0) {
                addProjectStaff(project, jobTypes.get(0), false, true, rh.getNaciVolunT());
            }

            if (rh.getNaciVolunA() != 0) {
                addProjectStaff(project, jobTypes.get(1), false, true, rh.getNaciVolunA());
            }

            if (rh.getNaciVolunO() != 0) {
                addProjectStaff(project, jobTypes.get(2), false, true, rh.getNaciVolunO());
            }

            if (rh.getExtanRemuT() != 0) {
                addProjectStaff(project, jobTypes.get(0), true, false, rh.getExtanRemuT());
            }

            if (rh.getExtranRemuA() != 0) {
                addProjectStaff(project, jobTypes.get(1), true, false, rh.getExtranRemuA());
            }

            if (rh.getExtranRemuO() != 0) {
                addProjectStaff(project, jobTypes.get(2), true, false, rh.getExtranRemuO());
            }

            if (rh.getExtanVolunT() != 0) {
                addProjectStaff(project, jobTypes.get(0), true, true, rh.getExtanVolunT());
            }

            if (rh.getExtanVolunA() != 0) {
                addProjectStaff(project, jobTypes.get(1), true, true, rh.getExtanVolunA());
            }

            if (rh.getExtanVolunO() != 0) {
                addProjectStaff(project, jobTypes.get(2), true, true, rh.getExtanVolunO());
            }

            project.setStaffLivingInAmazon(rh.getResidentesAmazonia());
            project.setStaffPartOfCommunity(rh.getMiembrosComunidadbenefi());

            em.persist(project);

        }
    }

    private void addProjectStaff(Project project, StaffJobType jobType,
            boolean isForeign, boolean isVolunteer, int quantity) {

        ProjectStaff ps = new ProjectStaff(isForeign, isVolunteer, quantity,
                jobType, project);
        em.persist(ps);
    }

    private Project getProject(RecursosHumanos rh, List<Project> projects) {
        String projectCode = rh.getCodigo();
        return getProjectByCode(projects, projectCode);
    }

    private List<StaffJobType> seedJobTypes() {

        List<StaffJobType> jobTypes = new ArrayList();

        for (String job : JOB_TYPES) {
            StaffJobType sjt = new StaffJobType(job);
            em.persist(sjt);
            jobTypes.add(sjt);
        }

        return jobTypes;
    }

    @Override
    public void run() throws FileNotFoundException {
        List<RecursosHumanos> list = read(RecursosHumanos.class);
        write(list);
    }

}
