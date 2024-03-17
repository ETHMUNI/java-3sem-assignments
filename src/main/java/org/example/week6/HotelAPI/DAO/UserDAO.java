package org.example.week6.HotelAPI.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week6.HotelAPI.Security.User;
import org.example.week6.HotelAPI.Security.Role;
import org.example.week6.HotelAPI.exceptions.AuthException;


import java.util.List;

public class UserDAO {

    private static UserDAO instance;
    private static EntityManagerFactory emf;

    public static UserDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserDAO();
        }
        return instance;
    }

    public User getVerifiedUser(String username, String password) throws AuthException {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);

            if (user == null || !user.verifyPassword(password)) {
                throw new AuthException(401, "Invalid user name or password");
            }
            em.getTransaction().commit();
            return user;
        }
    }

    public User registerUser(String username, String password, String user_role) throws AuthException {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            User user = new User(username, password);
            Role role = em.find(Role.class, user_role);

            if (role == null) {
                role = createRole(user_role);
            }

            user.addRole(role);
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            throw new AuthException(400, "Username already exists");
        }
    }

    public Role createRole(String role) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Role newRole = new Role(role);
            em.persist(newRole);
            em.getTransaction().commit();
            return newRole;
        }
    }

    public User read(String userName) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, userName);
            em.getTransaction().commit();
            return user;
        }
    }

    public List<User> readAll() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            em.getTransaction().commit();
            return users;
        }
    }


    public User update(String userName, User user) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User userToUpdate = em.find(User.class, userName);
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setUserPassword(user.getUserPassword());
            em.getTransaction().commit();
            return userToUpdate;
        }
    }

    public void delete(String userName) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, userName);
            em.remove(user);
            em.getTransaction().commit();
        }
    }


    public boolean validatePrimaryKey(String userName) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, userName);
            em.getTransaction().commit();
            return user != null;
        }
    }

}