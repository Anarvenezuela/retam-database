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

    public OrganizationMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Institucion> elements) {

        Query findCountries = em.createNamedQuery("Country.findAll");
        List<Country> countries = findCountries.getResultList();
        processIDZero(elements, countries);
        processNonZeroIds(elements, countries);
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

    /**
     * Gets the organizations with non-zero IDs. These ones have redundant data
     * in the database.
     *
     * @param organizations
     * @return
     */
    private List<Institucion> getIDNonZeroOrgs(List<Institucion> organizations) {
        return organizations.stream()
                .filter(o -> o.getCod_InstitucionEjecutora() != 0)
                .collect(toList());
    }

    /**
     * Process all the rows that are in InstitucionesEjecutoras table, but not
     * in Proyectos. Since their information is incomplete, we fill the missing
     * fields with "Not Available". Because of redundancy, an organization with
     * the same id may appear multiple times and names may differ. We take the
     * longest name as the more "complete" name.
     *
     * @param organizations list of all organizations. Ordered by id.
     * @param countries list of available countries
     */
    private void processIDZero(List<Institucion> organizations,
            List<Country> countries) {

        List<Institucion> orgsIdZero = getIDZeroOrgs(organizations);
        Country notAvailableCountry = getNotAvailableCountry(countries);

        //Initialize first element
        int currentCode = orgsIdZero.get(0).getCod_InstitucionEjecutoraIE();
        String longestName = "";

        for (Institucion i : orgsIdZero) {

            // We changed id and collected all its info, store it.
            if (i.getCod_InstitucionEjecutoraIE() != currentCode) {
                Organization o = createIdZeroOrg(currentCode,
                        longestName, notAvailableCountry);

                em.persist(o);
                longestName = "";
                currentCode = i.getCod_InstitucionEjecutoraIE();
            }

            longestName = getLongest(longestName, i.getInstitucionIE());
        }

        // Insert last
        Organization o = createIdZeroOrg(currentCode, longestName,
                notAvailableCountry);
        em.persist(o);
    }

    /**
     * Creates an organization using N/A to fill its fields.
     */
    public static Organization createIdZeroOrg(int code, String name, Country notAvailable) {
        Organization o = new Organization();
        o.setCode(code);
        o.setName(name);
        o.setAddress(NOT_AVAILABLE);
        o.setCity(NOT_AVAILABLE);
        o.setIdCountry(notAvailable);
        return o;
    }

    /**
     * Process Organizations with non-zero IDs. Because of redundancy multiple
     * organizations may have the same code. We put them together and assume
     * that the longest field is the one with the most complete information.
     *
     */
    private void processNonZeroIds(List<Institucion> organizations,
            List<Country> countries) {

        List<Institucion> orgsIdNonZero = getIDNonZeroOrgs(organizations);

        //initialize
        Organization org = new Organization();
        int currentCode = orgsIdNonZero.get(0).getCod_InstitucionEjecutora();

        for (Institucion inst : orgsIdNonZero) {

            if (inst.getCod_InstitucionEjecutora() != currentCode) {

                org.setCode(currentCode);
                org.setFieldsToNullIfEmpty();
                checkAddress(org);
                em.persist(org);

                org = new Organization();
                currentCode = inst.getCod_InstitucionEjecutora();
            }

            updateCurrentOrg(org, inst, countries);
        }

        // save last
        org.setCode(currentCode);
        org.setFieldsToNullIfEmpty();
        em.persist(org);
    }

    /**
     * Sets the address to N/A if it's null. There's just one org without
     * address in the project table...
     */
    private void checkAddress(Organization o) {
        if (o.getAddress() == null) {
            o.setAddress(NOT_AVAILABLE);
        }
    }

    private String longestInstitutionName(String currentName, Institucion i) {
        return getLongest(currentName, i.getInstitucion(),
                i.getInstitucionIE(), i.getNombreInstitucion());
    }

    private String longestAddress(String currentAddress, Institucion i) {
        return getLongest(currentAddress, i.getDireccion(),
                i.getDireccionInstitucion(), i.getDireccionProfesional());
    }

    private String longestCity(String currentCity, Institucion i) {
        return getLongest(currentCity, i.getCiudad(),
                i.getCiudadInstitucion(), i.getCiudadProfesional());
    }

    private String longestPhone1(String currentPhone1, Institucion i) {
        return getLongest(currentPhone1, i.getTelefono1(),
                i.getTlf1Proyecto());
    }

    private String longestPhone2(String currentPhone2, Institucion i) {
        return getLongest(currentPhone2, i.getTelefono2());
    }

    private String longestFax1(String currentFax1, Institucion i) {
        return getLongest(currentFax1, i.getFaxProyecto(),
                i.getFax1Proyecto());
    }

    private String longestFax2(String currentFax2, Institucion i) {
        return getLongest(currentFax2, i.getFax2Proyecto());
    }

    private String longestMail(String currentMail, Institucion i) {
        return getLongest(currentMail, i.getEmail(), i.getEmailProyecto());
    }

    private String longestWebsite(String currentWebsite, Institucion i) {
        return getLongest(currentWebsite, i.getPaginaWeb());
    }

    private String longestPostalCode(String currentPostalCode, Institucion i) {
        return getLongest(currentPostalCode, i.getApartadoAereo());
    }

    private Country validCountry(Institucion i, List<Country> countries) {

        Country c;

        if (i.getPaisInstitucion() != null) {
            c = getCountryByName(countries, i.getPaisInstitucion());
        } else if (i.getPaisProfesional() != null) {
            c = getCountryByName(countries, i.getPaisProfesional());
        } else {
            c = null;
        }

        return c;
    }

    private void updateCurrentOrg(Organization o, Institucion i,
            List<Country> countries) {

        o.setAddress(longestAddress(o.getAddress(), i));
        o.setCity(longestCity(o.getCity(), i));
        o.setEmail(longestMail(o.getEmail(), i));
        o.setFax1(longestFax1(o.getFax1(), i));
        o.setFax2(longestFax2(o.getFax2(), i));
        o.setName(longestInstitutionName(o.getName(), i));
        o.setPhone1(longestPhone1(o.getPhone1(), i));
        o.setPhone2(longestPhone2(o.getPhone2(), i));
        o.setPostalCode(longestPostalCode(o.getPostalCode(), i));
        o.setWebsite(longestWebsite(o.getWebsite(), i));
        o.setIdCountry(validCountry(i, countries));
    }

}
