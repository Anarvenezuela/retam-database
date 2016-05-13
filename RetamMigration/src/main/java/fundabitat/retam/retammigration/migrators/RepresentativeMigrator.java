/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Organization;
import fundabitat.retam.models.Representative;
import fundabitat.retam.retammigration.oldmodels.Representante;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class RepresentativeMigrator extends AbstractMigrator<Representante> {

    public RepresentativeMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Representante> elements) {

        Query findOrganizations = em.createNamedQuery("Organization.findAll");
        List<Organization> orgs = findOrganizations.getResultList();

        for (Representante r : elements) {
            Representative rep = new Representative();
            rep.setName(longestName(r));
            Organization o = findById(orgs, r.getCod_InstitucionEjecutora());
            rep.setIdOrganization(o);
            rep.setProfession(r.getProfesion());
            rep.setPosition(longestCharge(r));

            em.persist(rep);
        }
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Representante> list = read(Representante.class);
        write(list);
    }

    private String longestName(Representante r) {
        return getLongest(r.getRepresentante(), 
                r.getRepresentanteInstitucion(), r.getNombreProfesional());
    }

    private String longestCharge(Representante r) {
        return getLongest(r.getCargoRepresentante(), 
                r.getCargoRepresentanteInst());
    }

    private Organization findById(List<Organization> orgs, int code) {
        return orgs.stream().
                filter(o -> o.getCode() == code).findFirst().get();
    }

}
