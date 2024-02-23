package org.example.week4.SchoolExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week4.config.HibernateConfig;

public class Populate {

    public static void main(String[] args) {
        EntityManager entityManager = HibernateConfig.buildEntityFactoryConfig().createEntityManager();
        entityManager.getTransaction().begin();

        populateData(entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private static void populateData(EntityManager entityManager) {
        Student student1 = new Student();
        student1.setFirstName("Anders");
        student1.setLastName("And");

        Student student2 = new Student();
        student2.setFirstName("Andersine");
        student2.setLastName("And");

        entityManager.persist(student1);
        entityManager.persist(student2);


        Semester semester = new Semester();
        semester.setName("Spring 2024");
        semester.getStudents().add(student1);
        semester.getStudents().add(student2);

        entityManager.persist(semester);


        Teacher teacher = new Teacher();
        teacher.setFirstName("Jörg");
        teacher.setLastName("Oertel");
        teacher.getTeaches().add(semester);

        entityManager.persist(teacher);
    }
}
