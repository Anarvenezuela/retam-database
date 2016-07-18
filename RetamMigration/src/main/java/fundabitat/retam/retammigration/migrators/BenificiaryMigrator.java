/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Project;
import fundabitat.retam.retammigration.oldmodels.Beneficiarios;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author marcos
 */
public class BenificiaryMigrator extends AbstractMigrator<Beneficiarios> {

    public BenificiaryMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Beneficiarios> elements) {
        Query findCountries = em.createNamedQuery("Project.findAll");
        List<Project> projects = findCountries.getResultList();

        elements.stream().forEach(x -> System.out.println(x));
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Beneficiarios> list = read(Beneficiarios.class);
        write(list);
    }

}
