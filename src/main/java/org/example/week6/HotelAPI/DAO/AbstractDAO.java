package org.example.week6.HotelAPI.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.HibernateConfig;

import java.util.List;

public abstract class AbstractDAO<T, V> implements IDAO<T, V> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private Class<T> genericClass;

    public AbstractDAO(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public List<T> getAll(V v) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT t FROM " + genericClass.getName() + " t";
        List<T> results = em.createQuery(jpql, genericClass).getResultList();
        em.close();
        return results;
    }

    @Override
    public T getById(int id) {
        EntityManager em = emf.createEntityManager();
        T t = em.find(genericClass, id);
        em.close();
        return t;
    }

    @Override
    public T create(V v) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
        em.close();
        return (T) v;
    }

    @Override
    public T update(V v) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(v);
        em.getTransaction().commit();
        em.close();
        return (T) v;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T t = em.find(genericClass, id);
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }
}
