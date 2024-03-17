package org.example.week6.HotelAPI.DAO;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week5.Exercise_wed_thur.DAO.AbstractDAO;
import org.example.week5.Exercise_wed_thur.Entities.HotelDTO;
import org.example.week5.Exercise_wed_thur.Entities.RoomDTO;

import java.util.List;

public class HotelDAO extends AbstractDAO<HotelDTO, EntityManagerFactory> {

    public HotelDAO(Class<HotelDTO> hotelDTOClass, EntityManagerFactory entityManagerFactory) {
        super(HotelDTO.class);
    }


    public List<RoomDTO> getAllRoomsForHotel(int hotelId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT r FROM RoomDTO r WHERE r.hotel.id = :hotelId";
            List<RoomDTO> rooms = em.createQuery(jpql, RoomDTO.class)
                    .setParameter("hotelId", hotelId)
                    .getResultList();
            return rooms;
        } finally {
            em.close();
        }
    }

    public List<HotelDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT h FROM HotelDTO h ORDER BY h.name ASC";
            List<HotelDTO> results = em.createQuery(jpql, HotelDTO.class).getResultList();
            return results;
        } finally {
            em.close();
        }
    }


    public HotelDTO getById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            HotelDTO hotel = em.find(HotelDTO.class, id);
            return hotel;
        } finally {
            em.close();
        }
    }


    public HotelDTO create(HotelDTO hotel) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hotel);
            em.getTransaction().commit();
            return hotel;
        } finally {
            em.close();
        }
    }


    public HotelDTO update(HotelDTO hotel) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hotel);
            em.getTransaction().commit();
            return hotel;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            HotelDTO hotel = em.find(HotelDTO.class, id);
            em.remove(hotel);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
