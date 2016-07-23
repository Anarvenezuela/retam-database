/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration.migrators;

import fundabitat.retam.retammigration.oldmodels.Objetivos;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author marcos
 */
public class ObjectiveMigrator extends AbstractMigrator<Objetivos> {

    public ObjectiveMigrator(String filename) {
        this.filename = filename;
    }

    @Override
    protected void process(List<Objetivos> elements) {
        elements.stream().forEach(x -> System.out.println(x));
    }

    @Override
    public void run() throws FileNotFoundException {
        List<Objetivos> list = read(Objetivos.class);
        write(list);
    }

}
