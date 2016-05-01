/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import fundabitat.retam.models.Country;
import javax.persistence.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcos
 */
public class JPATest {

    public JPATest() {
    }

    @Test
    public void hello() throws Exception {

        // Creating objects representing some products
        Country country1 = new Country();
        country1.setName("Venezuela3");

        /*Product product2 = new Product();
		product2.setId(2);
		product2.setName("Guitar");*/
        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Retam Database PU");

        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persisting the product entity objects
        em.persist(country1);

        tx.commit();

        // Querying the contents of the database using JPQL query
        /*Query q = em.createQuery("SELECT p FROM Product p");

        @SuppressWarnings("unchecked")
        List<Product> results = q.getResultList();

        System.out.println("List of products\n----------------");

        for (Product p : results) {

            System.out.println(p.getName() + " (id=" + p.getId() + ")");
        }*/
        // Closing connection
        em.close();

        emf.close();

    }
}
