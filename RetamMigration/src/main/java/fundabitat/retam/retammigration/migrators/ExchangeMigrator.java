/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Country;
import fundabitat.retam.models.Exchange;
import fundabitat.retam.models.Project;
import fundabitat.retam.retammigration.oldmodels.Intercambio;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class ExchangeMigrator extends AbstractMigrator<Intercambio> {

    public ExchangeMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Intercambio> elements) {

        Query findProjects = em.createNamedQuery("Project.findAll");
        List<Project> projects = findProjects.getResultList();

        Query findCountries = em.createNamedQuery("Country.findAll");
        List<Country> countries = findCountries.getResultList();

        for (Intercambio i : elements) {
            if (isExchangeUseless(i)) {
                continue;
            }

            Exchange e = new Exchange();
            e.setProjectName(i.getProyecto());
            e.setOrganization(i.getInstitucion());
            e.setRepresentative(i.getResponsable());
            e.setAddress(i.getDireccion());
            e.setCity(i.getCiudad());
            e.setPhone(i.getTelefono());
            e.setFax(i.getFax());
            e.setEmail(i.getEmail());
            e.setWebpage(i.getPaginaWeb());
            e.setExchangeType(i.getTipoIntercambio());
            e.setContactExists(i.isContacto());
            e.setNoContactReason(i.getTextoNoContacto());

            Project project = getProjectByCode(projects, i.getCodigo());
            e.setIdProject(project);

            Country country;
            String countryName = i.getPais();

            if (isNullOrEmpty(countryName)) {
                country = getNotAvailableCountry(countries);
            } else {
                System.out.println(countryName);
                country = getCountryByName(countries, countryName);
            }

            e.setIdCountry(country);

            em.persist(e);
        }

    }

    /**
     * An exchange is useless when all its fields are null or empty. Makes no
     * sense to add it to the database.
     */
    private boolean isExchangeUseless(Intercambio i) {
        boolean res = isNullOrEmpty(i.getTipoIntercambio());
        res &= isNullOrEmpty(i.getInstitucion());
        res &= isNullOrEmpty(i.getDireccion());
        res &= isNullOrEmpty(i.getCiudad());
        res &= isNullOrEmpty(i.getPais());
        res &= isNullOrEmpty(i.getTelefono());
        res &= isNullOrEmpty(i.getFax());
        res &= isNullOrEmpty(i.getEmail());
        res &= isNullOrEmpty(i.getPaginaWeb());
        res &= isNullOrEmpty(i.getProyecto());
        res &= isNullOrEmpty(i.getResponsable());
        res &= isNullOrEmpty(i.getTextoNoContacto());
        return res;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Intercambio> list = read(Intercambio.class);
        write(list);
    }

}
