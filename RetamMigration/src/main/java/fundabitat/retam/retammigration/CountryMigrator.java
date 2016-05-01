/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration;

import fundabitat.retam.retammigration.oldmodels.Country;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author marcos
 */
public class CountryMigrator extends AbstractMigrator {

    @Override
    public void write() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Country> list = read(Country.class);

        for (Country c : list) {
            System.out.println(c);
        }
    }

}
