/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration;

import fundabitat.retam.persistence.PersistenceManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcos
 */
public class Main {

    public static void main(String[] args) throws Exception {

        PersistenceManager manager = PersistenceManager.getInstance();
        EntityManagerFactory factory = manager.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();

        MigrationManager.migrate(em);

        em.close();
        manager.closeEntityManagerFactory();
    }

}
