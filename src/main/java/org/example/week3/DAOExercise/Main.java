package org.example.week3.DAOExercise;

import jakarta.persistence.EntityManager;
import org.example.week3.HibernateConfig;

public class Main {

    public static void main(String[] args) {

        EntityManager entityManager = HibernateConfig.buildEntityFactoryConfig().createEntityManager();
        entityManager.getTransaction().begin();

        StudentCRUD studentDAO = new StudentCRUD();

       /* Student student1 = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .age(25)
                .major("Computer Science")
                .build();*/

        //Student createdStudent = studentDAO.create(student1);

       Student readStudent = studentDAO.read(1);
        System.out.println(readStudent);

        //createdStudent.setMajor("Computer Engineering");
        //Student updatedStudent = studentDAO.update(createdStudent);

        //studentDAO.delete(1);


    }
}

/**
 * QUESTION: Explain the benefits of using a DAO architecture for separating database access logic from business logic.
 *
 * ANSWER:
 * Code is more organized and easier to test as business logic can be tested independently of database operations
 */