/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration;

import fundabitat.retam.retammigration.migrators.CountryMigrator;
import fundabitat.retam.retammigration.migrators.AbstractMigrator;
import fundabitat.retam.retammigration.migrators.DescriptorMigrator;
import fundabitat.retam.retammigration.migrators.OrganizationMigrator;
import fundabitat.retam.retammigration.migrators.ProjectMigrator;
import fundabitat.retam.retammigration.migrators.ProjectOrganizationMigrator;
import fundabitat.retam.retammigration.migrators.RepresentativeMigrator;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import utils.ScriptRunner;

/**
 *
 * @author marcos
 */
public class MigrationManager {

    private static final Properties PROP = new Properties();
    public static char separator;
    public static String countryFile;
    public static String organizationFile;
    public static String representativeFile;
    public static String projectFile;
    public static String descriptorsFile;
    public static String projectOrgFile;

    private MigrationManager() {
    }

    public static void migrate(EntityManager manager) throws Exception {

        loadProperties();
        runScripts();

        List<AbstractMigrator> migrators = addMigrators();

        for (AbstractMigrator migrator : migrators) {
            migrator.setSeparator(separator);
            migrator.setEm(manager);
            migrator.run();
        }
    }

    private static List<AbstractMigrator> addMigrators() {

        List<AbstractMigrator> migrators = new ArrayList();

        AbstractMigrator migrator = new CountryMigrator(countryFile);
        migrators.add(migrator);

        migrator = new OrganizationMigrator(organizationFile);
        migrators.add(migrator);

        migrator = new RepresentativeMigrator(representativeFile);
        migrators.add(migrator);

        migrator = new ProjectMigrator(projectFile);
        migrators.add(migrator);

        migrator = new DescriptorMigrator(descriptorsFile);
        migrators.add(migrator);

        migrator = new ProjectOrganizationMigrator(projectOrgFile);
        migrators.add(migrator);

        return migrators;
    }

    private static void runScripts() throws IOException, SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        Connection mConnection;
        mConnection = DriverManager.getConnection("jdbc:sqlite:" + PROP.getProperty("databaseFile"));

        ScriptRunner runner = new ScriptRunner(mConnection, false, true);
        String[] scripts = getScripts();

        for (String script : scripts) {
            runner.runScript(new BufferedReader(new FileReader(script)));
        }
    }

    private static String[] getScripts() {
        String allScripts = PROP.getProperty("scripts");
        return allScripts.split(",");
    }

    private static void loadProperties() throws FileNotFoundException, IOException {
        FileInputStream propFile = new FileInputStream("src/main/resources/config.properties");
        PROP.load(propFile);
        separator = PROP.getProperty("separator").charAt(0);
        countryFile = PROP.getProperty("countryFile");
        organizationFile = PROP.getProperty("organizationFile");
        representativeFile = PROP.getProperty("representativeFile");
        projectFile = PROP.getProperty("projectFile");
        descriptorsFile = PROP.getProperty("descriptorFile");
        projectOrgFile = PROP.getProperty("projectOrgFile");
    }
}
