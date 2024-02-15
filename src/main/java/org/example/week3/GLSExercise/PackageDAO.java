package org.example.week3.GLSExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.CRUD;
import org.example.week3.HibernateConfig;

public class PackageDAO implements CRUD<GLSPackage> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public GLSPackage create(GLSPackage p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    public GLSPackage read(int id) {
        EntityManager em = emf.createEntityManager();
        GLSPackage p = em.find(GLSPackage.class, id);
        em.close();
        return p;
    }

    public GLSPackage update(GLSPackage p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        GLSPackage p = em.find(GLSPackage.class, id);
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }
}
