/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Project;
import fundabitat.retam.models.Representative;
import fundabitat.retam.retammigration.oldmodels.Proyecto;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class ProjectMigrator extends AbstractMigrator<Proyecto> {

    public ProjectMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Proyecto> elements) {

        Query findCountries = em.createNamedQuery("Country.findAll");
        List<Country> countries = findCountries.getResultList();

        Query findRepresentatives = em.createNamedQuery("Representative.findAll");
        List<Representative> representatives = findRepresentatives.getResultList();

        for (Proyecto p : elements) {
            Project project = processProject(p, countries, representatives);
            em.persist(project);
        }

    }

    @Override
    public void run() throws FileNotFoundException {
        List<Proyecto> list = read(Proyecto.class);
        write(list);
    }

    private Project processProject(Proyecto proyecto, List<Country> countries,
            List<Representative> representatives) {

        Project project = new Project();
        project.setCode(proyecto.getCod_Proyecto());
        project.setName(proyecto.getNombreProyecto());

        Country projectCountry = getCountryByName(countries, proyecto.getPais());
        project.setIdCountry(projectCountry);

        Representative projectRepresentative = getRepresentative(proyecto,
                representatives);
        project.setIdRepresentative(projectRepresentative);

        return project;
    }

    /**
     * Gets the representative for this project. Representatives in the
     * project.csv must match the ones in the representative.csv
     */
    private Representative getRepresentative(Proyecto proyecto,
            List<Representative> representatives) {

        String representativeName = getLongest(proyecto.getRepresentante(),
                proyecto.getRepresentanteInstitucion(),
                proyecto.getNombreProfesional());

        return representatives.stream()
                .filter(r -> r.getName().equals(representativeName)
                        && r.getIdOrganization().getCode() == proyecto.getCod_InstitucionEjecutora())
                .findFirst().get();
    }

}
