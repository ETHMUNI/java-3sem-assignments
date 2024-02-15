package org.example.week3.DAOExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.CRUD;
import org.example.week3.HibernateConfig;

public class StudentCRUD implements CRUD<Student> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    @Override
    public Student create(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        return student;
    }
    @Override
    public Student read(int id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    @Override
    public Student update(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
        return student;
    }

    @Override
    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        em.remove(student);
        em.getTransaction().commit();
        em.close();
    }

}
