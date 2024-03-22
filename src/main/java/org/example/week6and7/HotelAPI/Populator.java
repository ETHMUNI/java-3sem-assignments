package org.example.week6and7.HotelAPI;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week6and7.HotelAPI.Config.HibernateConfig;
import org.example.week6and7.HotelAPI.Entities.HotelDTO;
import org.example.week6and7.HotelAPI.Entities.RoomDTO;
import org.example.week6and7.HotelAPI.Security.Role;
import org.example.week6and7.HotelAPI.Security.User;

public class Populator {

    public void createHotelsAndRooms(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create Hotels without specifying the id
            HotelDTO hotel1 = new HotelDTO("Hotel Sunshine", "Sunshine Boulevard, 123", 100);
            HotelDTO hotel2 = new HotelDTO("Hotel Moonlight", "Moonlight Avenue, 456", 50);

            // Persist hotels
            em.persist(hotel1);
            em.persist(hotel2);

            // Create Rooms and associate with Hotels without specifying the id
            RoomDTO room1 = new RoomDTO(hotel1, 101, 150);
            RoomDTO room2 = new RoomDTO(hotel1, 102, 180);
            RoomDTO room3 = new RoomDTO(hotel2, 201, 200);
            RoomDTO room4 = new RoomDTO(hotel2, 202, 220);

            // Persist Rooms
            em.persist(room1);
            em.persist(room2);
            em.persist(room3);
            em.persist(room4);

            em.getTransaction().commit();

            System.out.println("Hotels and Rooms populated in DB.");
        }
    }
    public void createUsersAndRoles(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create roles
            Role roleAdmin = new Role("ADMIN");
            Role roleUser = new Role("USER");

            // Persist roles
            em.persist(roleAdmin);
            em.persist(roleUser);

            // Create users and assign roles
            User user1 = new User("adminUser", "adminPass");
            user1.addRole(roleAdmin); // Assign admin role

            User user2 = new User("regularUser", "userPass");
            user2.addRole(roleUser); // Assign user role

            // Persist users
            em.persist(user1);
            em.persist(user2);

            em.getTransaction().commit();

            System.out.println("Users and Roles populated in DB.");
        }
    }

    public static void main(String[] args) {
        Populator populator = new Populator();
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // Populate hotels and rooms
        populator.createHotelsAndRooms(emf);

        // Populate users and roles
        populator.createUsersAndRoles(emf);
    }
}
