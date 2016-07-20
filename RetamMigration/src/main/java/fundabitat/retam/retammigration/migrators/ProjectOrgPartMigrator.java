/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Organization;
import fundabitat.retam.models.ParticipationType;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.ProjectOrganizationParticipation;
import fundabitat.retam.retammigration.oldmodels.OtrasParticipantes;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class ProjectOrgPartMigrator extends AbstractMigrator<OtrasParticipantes> {

    public ProjectOrgPartMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<OtrasParticipantes> elements) {

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        Query findOrganizations = em.createNamedQuery("Organization.findAll");
        List<Organization> orgs = findOrganizations.getResultList();

        Query findParticipation = em.createNamedQuery("ParticipationType.findAll");
        List<ParticipationType> participations = findParticipation.getResultList();

        Query findCountries = em.createNamedQuery("Country.findAll");
        List<Country> countries = findCountries.getResultList();

        processOtrasPart(elements, projects, orgs, countries, participations);
    }

    private void processOtrasPart(List<OtrasParticipantes> elements,
            List<Project> projects, List<Organization> orgs,
            List<Country> countries, List<ParticipationType> participations) {

        for (OtrasParticipantes o : elements) {
            Project project = getProject(projects, o.getCodigo());
            Organization org;

            try {
                org = getOrg(orgs, o.getCodigoOtras());
            } catch (Exception e) {
                org = OrganizationMigrator.createIdZeroOrg(o.getCodigoOtras(),
                        o.getNombreInstitucion(), getNotAvailableCountry(countries));
                em.persist(org);
                orgs.add(org);
            }

            List<ParticipationType> partTypes = getPartTypes(participations,
                    o.getListParticipationType());

            for (ParticipationType p : partTypes) {
                ProjectOrganizationParticipation part = new ProjectOrganizationParticipation(project, org, p);
                em.persist(part);
            }

        }
    }

    @Override
    public void run() throws FileNotFoundException {
        List<OtrasParticipantes> list = read(OtrasParticipantes.class);
        write(list);
    }

    private Project getProject(List<Project> projects, String code) {
        return projects.stream().filter(p -> p.getCode().equals(code))
                .findFirst().get();
    }

    private Organization getOrg(List<Organization> projects, int code) {
        return projects.stream().filter(p -> p.getCode() == code)
                .findFirst().get();
    }

    private List<ParticipationType> getPartTypes(List<ParticipationType> partTypes, List<String> codes) {
        return partTypes.stream()
                .filter(p -> codes.contains(p.getCode()))
                .collect(Collectors.toList());
    }

}
