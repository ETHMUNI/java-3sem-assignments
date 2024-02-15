package org.example.week3.PointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.HibernateConfig;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class QueryCRUDTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static QueryDAO queryDAO;
    @BeforeAll
    public static void setUp() {

        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        queryDAO = new QueryDAO();
    }

    @AfterAll
    public static void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void storePointsInDatabase() {
        queryDAO.storePointsInDatabase();
        // Verify that 1000 points were added. This will require fetching the count from the database.
        int count = (int) em.createQuery("SELECT COUNT(p) FROM Point p").getSingleResult();
        assertEquals(1000, count);
    }

    @Test
    void findNumberOfPoints() {
        queryDAO.storePointsInDatabase();

        int count = (int) em.createQuery("SELECT COUNT(p) FROM Point p").getSingleResult();
        assertEquals(1000, count);
    }

    @Test
    void findAverageXValue() {
        queryDAO.storePointsInDatabase();

        double average = (double) em.createQuery("SELECT AVG(p.x) FROM Point p").getSingleResult();
        assertEquals(499.5, average);
    }

    @Test
    void retrieveAllPoints() {
        queryDAO.storePointsInDatabase();

        int count = (int) em.createQuery("SELECT COUNT(p) FROM Point p").getSingleResult();
        assertEquals(1000, count);
    }

    @Test
    void closeDatabaseConnection() {
        queryDAO.closeDatabaseConnection();

        assertFalse(em.isOpen());
    }
}