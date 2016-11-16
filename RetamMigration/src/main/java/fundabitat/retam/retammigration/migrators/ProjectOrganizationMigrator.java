/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Organization;
import fundabitat.retam.models.Project;
import fundabitat.retam.retammigration.oldmodels.ProyectoInstitucion;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class ProjectOrganizationMigrator extends AbstractMigrator<ProyectoInstitucion> {

    public ProjectOrganizationMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<ProyectoInstitucion> elements) {
        Query findOrgs = em.createNamedQuery("Organization.findAll");
        Query findProjects = em.createNamedQuery("Project.findAll");

        List<Organization> orgs = findOrgs.getResultList();
        List<Project> projects = findProjects.getResultList();

        for (ProyectoInstitucion po : elements) {

            Organization org = getOrgByCode(orgs, po.getCod_InstitucionEjecutora());
            Project project = getProjectByCode(projects, po.getCod_Proyecto().trim().toUpperCase());

            project.getOrganizationCollection().add(org);
            em.persist(project);
        }
    }

    @Override
    public void run() throws FileNotFoundException {
        List<ProyectoInstitucion> list = read(ProyectoInstitucion.class);
        write(list);
    }

    private Organization getOrgByCode(List<Organization> list, int code) {
        return list.stream().filter(o -> o.getCode() == code)
                .findFirst().get();
    }

}
