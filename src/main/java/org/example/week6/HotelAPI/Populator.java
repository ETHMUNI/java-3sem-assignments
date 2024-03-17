package org.example.week6.HotelAPI;

import org.example.week5.Exercise_wed_thur.Entities.HotelDTO;
import org.example.week5.Exercise_wed_thur.Entities.RoomDTO;
import org.example.week5.Exercise_wed_thur.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

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

    public static void main(String[] args) {
        Populator populator = new Populator();
        populator.createHotelsAndRooms(HibernateConfig.getEntityManagerFactory());
    }
}
