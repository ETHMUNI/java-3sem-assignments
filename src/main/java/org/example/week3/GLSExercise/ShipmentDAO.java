package org.example.week3.GLSExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.CRUD;
import org.example.week3.HibernateConfig;

public class ShipmentDAO implements CRUD<Shipment> {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    @Override
    public Shipment create(Shipment shipment) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(shipment);
        em.getTransaction().commit();
        em.close();
        return shipment;
    }

    @Override
    public Shipment read(int id) {
        EntityManager em = emf.createEntityManager();
        Shipment foundShipment = em.find(Shipment.class, id);
        em.close();
        return foundShipment;
    }

    @Override
    public Shipment update(Shipment shipment) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(shipment);
        em.getTransaction().commit();
        em.close();
        return shipment;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Shipment shipment = em.find(Shipment.class, id);
        em.remove(shipment);
        em.getTransaction().commit();
        em.close();
    }
}
