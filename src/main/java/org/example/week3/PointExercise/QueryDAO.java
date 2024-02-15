package org.example.week3.PointExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.week3.HibernateConfig;
import org.example.week3.PointExercise.Point;

public class QueryDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    EntityManager em = emf.createEntityManager();

    public Query storePointsInDatabase() {
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
        return null;
    }

    public Query findNumberOfPoints() {
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        q1.getSingleResult();
        return q1;
    }

    public Query findAverageXValue() {
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        q2.getSingleResult();
        return q2;
    }

    public Query retrieveAllPoints() {
        Query query = em.createQuery("SELECT p FROM Point p");
        query.getSingleResult();
        return query;
    }

    public void closeDatabaseConnection() {
        em.close();
        emf.close();
    }



}
