/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration;

import fundabitat.retam.retammigration.migrators.CountryMigrator;
import fundabitat.retam.retammigration.migrators.AbstractMigrator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author marcos
 */
public class MigrationManager {

    // TODO config file
    public static final char SEPARATOR = '|';
    public static final String COUNTRY_FILE = "src/main/resources/exportData/exportPais.csv";

    private MigrationManager() {
    }

    public static void migrate(EntityManager manager) throws FileNotFoundException {
        List<AbstractMigrator> migrators = new ArrayList();

        AbstractMigrator countryMigrator = new CountryMigrator(COUNTRY_FILE);

        migrators.add(countryMigrator);

        for (AbstractMigrator migrator : migrators) {
            migrator.setSeparator(SEPARATOR);
            migrator.setEm(manager);
            migrator.run();
        }
    }
}
