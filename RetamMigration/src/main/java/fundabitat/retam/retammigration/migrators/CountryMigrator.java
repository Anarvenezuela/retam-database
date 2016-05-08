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

/**
 *
 * @author marcos
 */
public class CountryMigrator extends AbstractMigrator<Pais> {

    public CountryMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Pais> elements) {

        for (Pais p : elements) {
            Country country = new Country();
            country.setName(p.getPais().trim());
            country.setIdCountry(p.getIdPais());
            em.persist(country);
        }

    }

    @Override
    public void run() throws FileNotFoundException {
        List<Pais> list = read(Pais.class);
        write(list);
    }

}
