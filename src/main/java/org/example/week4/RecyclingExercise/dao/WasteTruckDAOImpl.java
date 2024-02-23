package org.example.week4.RecyclingExercise.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.week3.HibernateConfig;
import org.example.week4.RecyclingExercise.model.Driver;
import org.example.week4.RecyclingExercise.model.WasteTruck;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO {

    private EntityManager em;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        WasteTruck wasteTruck = new WasteTruck(brand, capacity, registrationNumber);
        em.persist(wasteTruck);
        em.getTransaction().commit();
        em.close();
        return wasteTruck.getId();
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        em = emf.createEntityManager();
        WasteTruck wasteTruck = em.find(WasteTruck.class, id);
        em.close();
        return wasteTruck;
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        wasteTruck.setAvailable(available);
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteWasteTruck(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        WasteTruck wasteTruck = em.find(WasteTruck.class, id);
        em.remove(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        wasteTruck.setDriver(driver);
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        wasteTruck.setDriver(null);
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT w FROM WasteTruck w WHERE w.isAvailable = true");
        List<WasteTruck> availableTrucks = query.getResultList();
        em.close();
        return availableTrucks;
    }
}
