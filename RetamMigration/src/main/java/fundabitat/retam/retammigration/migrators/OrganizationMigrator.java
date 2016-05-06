/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Organization;
import fundabitat.retam.retammigration.oldmodels.Institucion;
import java.io.FileNotFoundException;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class OrganizationMigrator extends AbstractMigrator<Institucion> {

    private static final String NOT_AVAILABLE = "No Disponible";

    public OrganizationMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Institucion> elements) {

        Query findCountries = em.createNamedQuery("Country.findAll");
        List<Country> countries = findCountries.getResultList();
        processIDZero(elements, countries);
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Institucion> list = read(Institucion.class);
        write(list);
    }

    /**
     * Gets organizations with ID 0. Organizations with this ID do not have all
     * their data. The data is missing in the old database.
     *
     * @param organizations
     * @return
     */
    private List<Institucion> getIDZeroOrgs(List<Institucion> organizations) {
        return organizations.stream()
                .filter(o -> o.getCod_InstitucionEjecutora() == 0)
                .collect(toList());
    }

    private List<Institucion> getIDNonZeroOrgs(List<Institucion> organizations) {
        return organizations.stream()
                .filter(o -> o.getCod_InstitucionEjecutora() != 0)
                .collect(toList());
    }

    private void processIDZero(List<Institucion> organizations,
            List<Country> countries) {

        List<Institucion> orgs = getIDZeroOrgs(organizations);
        Country notAvailableCountry = getNotAvailableCountry(countries);

        int currentCode = orgs.get(0).getCod_InstitucionEjecutora2();
        String longestName = "";

        for (Institucion i : orgs) {

            if (i.getCod_InstitucionEjecutora2() != currentCode) {
                Organization o = createIdZeroOrg(currentCode,
                        longestName, notAvailableCountry);

                em.persist(o);
                longestName = "";
            }

            currentCode = i.getCod_InstitucionEjecutora2();

            if (i.getInstitucion2().length() > longestName.length()) {
                longestName = i.getInstitucion2().trim();
            }
        }

        // Insert last
        Organization o = createIdZeroOrg(currentCode, longestName,
                notAvailableCountry);
        em.persist(o);
    }

    private Country getNotAvailableCountry(List<Country> countries) {
        Country notAvailableCountry = countries.stream().
                filter(c -> c.getName().equals(NOT_AVAILABLE)).
                collect(toList()).get(0);
        return notAvailableCountry;
    }

    private Organization createIdZeroOrg(int code, String name, Country notAvailable) {
        Organization o = new Organization();
        o.setCode(code);
        o.setName(name);
        o.setAddress(NOT_AVAILABLE);
        o.setCity(NOT_AVAILABLE);
        o.setIdCountry(notAvailable);
        return o;
    }

}
