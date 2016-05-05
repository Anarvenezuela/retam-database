/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.models.Country;
import fundabitat.retam.retammigration.oldmodels.Pais;
import java.io.FileNotFoundException;
import java.util.List;
import javax.persistence.EntityTransaction;

/**
 *
 * @author marcos
 */
public class CountryMigrator extends AbstractMigrator<Pais> {

    public CountryMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    public void write(List<Pais> elements) {

        EntityTransaction trans = em.getTransaction();
        trans.begin();

        for (Pais p : elements) {

            Country country = new Country();
            country.setName(p.getPais());
            country.setIdCountry(p.getIdPais());
            em.persist(country);
        }

        trans.commit();
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Pais> list = read(Pais.class);
        write(list);
    }

}
