package org.example.week3.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.week3.HibernateConfig;

import java.util.List;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.buildEntityFactoryConfig();

    public static void main(String[] args) {

        /*createStudent(new Student("John", "Doe", "JohnDoe@gmail.com", 25));
        createStudent(new Student("Jane", "Doe", "JaneDoe@gmail.com", 23));*/

        Student student = readStudent(1);
        System.out.println(student);
    }

    public static void createStudent (Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Create a new student
        em.persist(student);
        // Commit the transaction
        em.getTransaction().commit();
        em.close();
    }

    public static Student readStudent (int id) {
        EntityManager em = emf.createEntityManager();
        // Read the student by id
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public static Student updateStudent (Student updStd) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Update the student by id and using setters
        Student student = em.find(Student.class, updStd.getId());
        student.setFirstName(updStd.getFirstName());
        student.setLastName(updStd.getLastName());
        student.setEmail(updStd.getEmail());
        student.setAge(updStd.getAge());
        em.getTransaction().commit();
        em.close();
        return student;
    }

    public static void deleteStudent(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        // Delete the student
        em.remove(student);
        // entity is in detached state after the transaction is committed
        em.getTransaction().commit();
        em.close();
    }

    public static List<Student> readAllStudents() {
        EntityManager em = emf.createEntityManager();
        // Read all students using JPQL query to select all students from the database table
        TypedQuery<Student> tq = em.createQuery("SELECT s FROM Student s", Student.class);
        // Return the list of students
        return tq.getResultList();
    }

}
