package org.example.week6and7.HotelAPI.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week6and7.HotelAPI.Entities.RoomDTO;

import java.util.List;

public class RoomDAO extends AbstractDAO<RoomDTO, RoomDTO> {

    public RoomDAO(Class<RoomDTO> roomDTOClass, EntityManagerFactory entityManagerFactory) {
        super(RoomDTO.class);
    }

    public List<RoomDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM RoomDTO r ORDER BY r.roomNumber ASC";
            List<RoomDTO> results = em.createQuery(jpql, RoomDTO.class).getResultList();
            return results;
        } finally {
            em.close();
        }
    }

    public RoomDTO getById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            RoomDTO room = em.find(RoomDTO.class, id);
            return room;
        } finally {
            em.close();
        }
    }

    public RoomDTO create(RoomDTO room) {
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

    public RoomDTO update(RoomDTO room) {
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
            RoomDTO room = em.find(RoomDTO.class, id);
            em.remove(room);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
