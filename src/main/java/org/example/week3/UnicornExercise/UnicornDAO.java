package org.example.week3.UnicornExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.HibernateConfig;

public class UnicornDAO implements CRUD<Unicorn, Unicorn> {

    EntityManagerFactory entityManagerFactoryConfig = HibernateConfig.getEntityManagerFactoryConfig();
    public Unicorn save(Unicorn unicorn) {
        EntityManager em = entityManagerFactoryConfig.createEntityManager();
        em.getTransaction().begin(); // start transaction
        em.persist(unicorn); // save the unicorn
        em.getTransaction().commit(); // commit transaction
        em.close(); // close the entity manager

        return unicorn;
    }
    public Unicorn findById(int id) {
        EntityManager em = entityManagerFactoryConfig.createEntityManager();
        Unicorn unicorn = em.find(Unicorn.class, id);
        return unicorn;
    }

    public Unicorn update(Unicorn unicorn) {
        EntityManager em = entityManagerFactoryConfig.createEntityManager();
        em.getTransaction().begin();
        em.merge(unicorn);
        em.getTransaction().commit();
        em.close();

        return unicorn;
    }

    public void delete(int id)
    {
        EntityManager em = entityManagerFactoryConfig.createEntityManager();
        em.getTransaction().begin();
        Unicorn unicorn = findById(id);
        if (unicorn != null)
        {
            em.remove(unicorn);
        }
        em.getTransaction().commit();
        em.close();
    }


}
