package org.example.week4;

import jakarta.persistence.EntityManager;
import org.example.week4.config.HibernateConfig;
import org.example.week4.RecyclingExercise.dao.DriverDAOImpl;
import org.example.week4.RecyclingExercise.dao.WasteTruckDAOImpl;

public class Main {

    public static void main(String[] args) {
        EntityManager entityManager = HibernateConfig.buildEntityFactoryConfig().createEntityManager();
        entityManager.getTransaction().begin();

        DriverDAOImpl driverDAO = new DriverDAOImpl();
        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl();

      //  driverDAO.saveDriver("John", "Doe", BigDecimal.valueOf(1000));


    }
}
