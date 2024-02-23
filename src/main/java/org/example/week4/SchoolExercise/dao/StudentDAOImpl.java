package org.example.week4.SchoolExercise.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.week3.DAOExercise.Student;
import org.example.week4.SchoolExercise.Semester;
import org.example.week4.SchoolExercise.StudentInfo;
import org.example.week4.SchoolExercise.Teacher;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO {

    private EntityManager entityManager;

    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName", Student.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        Query query = entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM Student s WHERE s.currentSemester.name = :semesterName");
        query.setParameter("semesterName", semesterName);
        return (long) query.getSingleResult();
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM Student s JOIN s.currentSemester.teachers t WHERE t = :teacher");
        query.setParameter("teacher", teacher);
        return (long) query.getSingleResult();
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        Query query = entityManager.createQuery("SELECT t FROM Teacher t JOIN t.teaches ts GROUP BY t ORDER BY COUNT(ts) DESC", Teacher.class);
        query.setMaxResults(1);
        return (Teacher) query.getSingleResult();
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        Query query = entityManager.createQuery("SELECT s FROM Semester s ORDER BY SIZE(s.students) ASC", Semester.class);
        query.setMaxResults(1);
        return (Semester) query.getSingleResult();
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        Query query = entityManager.createQuery("SELECT new org.example.week4.SchoolExercise.StudentInfo(s.firstName, s.lastName, s.currentSemester.name) FROM Student s WHERE s.id = :id", StudentInfo.class);
        query.setParameter("id", id);
        return (StudentInfo) query.getSingleResult();
    }
}
