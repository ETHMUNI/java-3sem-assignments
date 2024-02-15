package org.example.exercisesInClass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.week3.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateConfig.buildEntityFactoryConfig().createEntityManager();
        entityManager.getTransaction().begin();


        // JPQL query to select all employees
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        //System.out.println(query.getResultList());

        // JPQL query to select employees with a salary greater than a certain value.
        Query query2 = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > 60000");
        //System.out.println(query2.getResultList());

        // JPQL query to select employees from a specific department
        Query query3 = entityManager.createQuery("SELECT e FROM Employee e WHERE e.department = 'Finance'");
        System.out.println(query3.getResultList());
    }
}
