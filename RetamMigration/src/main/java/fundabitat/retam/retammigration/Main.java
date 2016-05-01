/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundabitat.retam.retammigration;

import fundabitat.retam.persistence.PersistenceManager;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcos
 */
public class Main {

    public static void main(String[] args) {

        PersistenceManager manager = PersistenceManager.getInstance();
        EntityManagerFactory factory = manager.getEntityManagerFactory();
        EntityManager em = factory.createEntityManager();

        try {
            MigrationManager.migrate(em);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        em.close();
        manager.closeEntityManagerFactory();
    }

}
