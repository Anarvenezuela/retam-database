/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.retammigration.oldmodels.RecursosHumanos;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author marcos
 */
public class HumanResourcesMigrator extends AbstractMigrator<RecursosHumanos> {

    public HumanResourcesMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<RecursosHumanos> elements) {
        elements.stream().forEach(e -> System.out.println(e));
    }

    @Override
    public void run() throws FileNotFoundException {
        List<RecursosHumanos> list = read(RecursosHumanos.class);
        write(list);
    }

}
