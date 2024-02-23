package org.example.week4.RecyclingExercise.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.week3.HibernateConfig;
import org.example.week4.RecyclingExercise.model.Driver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {

    private EntityManager em;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver driver = new Driver(name, salary, surname);
        driver.setName(name);
        driver.setSurname(surname);
        driver.setSalary(salary);
        driver.setDate(new Date());

        String id = driver.generateId();

        em.persist(driver);

        em.getTransaction().commit();
        em.close();
        return id;
    }

    @Override
    public Driver getDriverById(String id) {
        EntityManager em = emf.createEntityManager();
        Driver driver = em.find(Driver.class, id);
        em.close();
        return driver;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(driver);
        em.getTransaction().commit();
        em.close();
        return driver;
    }

    @Override
    public void deleteDriver(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver driver = em.find(Driver.class, id);
        em.remove(driver);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Driver d WHERE YEAR(d.date) = :year");
        query.setParameter("year", year);
        List<Driver> drivers = query.getResultList();
        em.close();
        return drivers;
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Driver d WHERE d.salary > 10000");
        List<Driver> drivers = query.getResultList();
        em.close();
        return drivers;
    }

    @Override
    public double fetchHighestSalary() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT MAX(d.salary) FROM Driver d");
        double highestSalary = (double) query.getSingleResult();
        em.close();
        return highestSalary;
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d.name FROM Driver d");
        List<String> firstNames = query.getResultList();
        em.close();
        return firstNames;
    }

    @Override
    public long calculateNumberOfDrivers() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT COUNT(d) FROM Driver d");
        long numberOfDrivers = (long) query.getSingleResult();
        em.close();
        return numberOfDrivers;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Driver d WHERE d.salary = (SELECT MAX(d2.salary) FROM Driver d2)");
        Driver driver = (Driver) query.getSingleResult();
        em.close();
        return driver;
    }
}
