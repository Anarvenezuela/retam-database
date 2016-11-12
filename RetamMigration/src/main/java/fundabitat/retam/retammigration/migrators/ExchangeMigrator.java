/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.retammigration.oldmodels.Intercambio;
import java.io.FileNotFoundException;
import java.util.List;

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

        elements.forEach(e -> System.out.println(e));

    }

    @Override
    public void run() throws FileNotFoundException {
        List<Intercambio> list = read(Intercambio.class);
        write(list);
    }

}
