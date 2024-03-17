package org.example.week6.HotelAPI.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class RoomDAO extends AbstractDAO<org.example.week6.HotelAPI.Entities.RoomDTO, org.example.week6.HotelAPI.Entities.RoomDTO> {

    public RoomDAO(Class<org.example.week6.HotelAPI.Entities.RoomDTO> roomDTOClass, EntityManagerFactory entityManagerFactory) {
        super(org.example.week6.HotelAPI.Entities.RoomDTO.class);
    }

    public List<org.example.week6.HotelAPI.Entities.RoomDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM RoomDTO r ORDER BY r.roomNumber ASC";
            List<org.example.week6.HotelAPI.Entities.RoomDTO> results = em.createQuery(jpql, org.example.week6.HotelAPI.Entities.RoomDTO.class).getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public org.example.week6.HotelAPI.Entities.RoomDTO getById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            org.example.week6.HotelAPI.Entities.RoomDTO room = em.find(org.example.week6.HotelAPI.Entities.RoomDTO.class, id);
            return room;
        } finally {
            em.close();
        }
    }

    public org.example.week6.HotelAPI.Entities.RoomDTO create(org.example.week6.HotelAPI.Entities.RoomDTO room) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
            return room;
        } finally {
            em.close();
        }
    }

    public org.example.week6.HotelAPI.Entities.RoomDTO update(org.example.week6.HotelAPI.Entities.RoomDTO room) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
            return room;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            org.example.week6.HotelAPI.Entities.RoomDTO room = em.find(org.example.week6.HotelAPI.Entities.RoomDTO.class, id);
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
