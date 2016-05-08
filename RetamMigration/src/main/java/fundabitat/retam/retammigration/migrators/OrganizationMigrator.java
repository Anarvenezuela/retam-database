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
     * Gets "Not available" from the list of countries.
     *
     * @param countries List of all countries in the DB.
     */
    private Country getNotAvailableCountry(List<Country> countries) {
        return getCountryByName(countries, NOT_AVAILABLE);
    }

    /**
     * Gets a country from the list of countries.
     */
    private Country getCountryByName(List<Country> countries, String name) {
        return countries.stream().
                filter(c -> c.getName().equals(name)).
                collect(toList()).get(0);
    }

    /**
     * Creates an organization using N/A to fill its fields.
     */
    private Organization createIdZeroOrg(int code, String name, Country notAvailable) {
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
        Organization org = initializeOrg();
        int currentCode = orgsIdNonZero.get(0).getCod_InstitucionEjecutora();

        for (Institucion inst : orgsIdNonZero) {

            if (inst.getCod_InstitucionEjecutora() != currentCode) {

                org.setCode(currentCode);
                em.persist(org);

                org = initializeOrg();
                currentCode = inst.getCod_InstitucionEjecutora();
            }

            updateCurrentOrg(org, inst, countries);
        }

        // save last
        org.setCode(currentCode);
        em.persist(org);
    }

    private String getLongest(String current, String newStr) {

        if (newStr != null && newStr.length() > current.length()) {
            return newStr.trim();
        }

        return current;
    }

    private String longestInstitutionName(String currentName, Institucion i) {
        currentName = getLongest(currentName, i.getInstitucion());
        currentName = getLongest(currentName, i.getInstitucionIE());
        currentName = getLongest(currentName, i.getNombreInstitucion());

        return currentName;
    }

    private String longestAddress(String currentAddress, Institucion i) {
        currentAddress = getLongest(currentAddress, i.getDireccion());
        currentAddress = getLongest(currentAddress, i.getDireccionInstitucion());
        currentAddress = getLongest(currentAddress, i.getDireccionProfesional());

        return currentAddress;
    }

    private String longestCity(String currentCity, Institucion i) {
        currentCity = getLongest(currentCity, i.getCiudad());
        currentCity = getLongest(currentCity, i.getCiudadInstitucion());
        currentCity = getLongest(currentCity, i.getCiudadProfesional());

        return currentCity;
    }

    private String longestPhone1(String currentPhone1, Institucion i) {
        currentPhone1 = getLongest(currentPhone1, i.getTelefono1());
        currentPhone1 = getLongest(currentPhone1, i.getTlf1Proyecto());

        return currentPhone1;
    }

    private String longestPhone2(String currentPhone2, Institucion i) {
        return getLongest(currentPhone2, i.getTelefono2());
    }

    private String longestFax1(String currentFax1, Institucion i) {
        currentFax1 = getLongest(currentFax1, i.getFaxProyecto());
        currentFax1 = getLongest(currentFax1, i.getFax1Proyecto());

        return currentFax1;
    }

    private String longestFax2(String currentFax2, Institucion i) {
        return getLongest(currentFax2, i.getFax2Proyecto());
    }

    private String longestMail(String currentMail, Institucion i) {
        currentMail = getLongest(currentMail, i.getEmail());
        currentMail = getLongest(currentMail, i.getEmailProyecto());

        return currentMail;
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

    private Organization initializeOrg() {
        Organization o = new Organization();
        o.setAddress("");
        o.setCity("");
        o.setEmail("");
        o.setFax1("");
        o.setFax2("");
        o.setName("");
        o.setPhone1("");
        o.setPhone2("");
        o.setPostalCode("");
        o.setWebsite("");

        return o;
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
