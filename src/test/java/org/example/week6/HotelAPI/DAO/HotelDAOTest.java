package org.example.week6.HotelAPI.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.week5.Exercise_wed_thur.Entities.HotelDTO;
import org.example.week6.HotelAPI.HibernateConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HotelDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static HotelDAO hotelDAO;

    @BeforeAll
    static void setUp() {
        // Ensure we are in test mode
        HibernateConfig.setTestMode(true);
        // Use the test EntityManagerFactory
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        em = emf.createEntityManager();
        hotelDAO = new HotelDAO(HotelDTO.class, emf);
    }

    @BeforeEach
    void resetDatabase() {
        em.getTransaction().begin();
        // Assuming you have a single table for simplicity. Adjust as necessary for your schema.
        em.createNativeQuery("TRUNCATE TABLE hoteldto CASCADE").executeUpdate();
        em.getTransaction().commit();
    }

    @AfterAll
    public static void tearDown() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }

    @Test
    void testCreateHotel() {
        HotelDTO newHotel = new HotelDTO("Test Hotel", "Test Address", 100);
        HotelDTO persistedHotel = hotelDAO.create(newHotel);

        assertNotNull(persistedHotel);
        assertNotNull(persistedHotel.getId());
        assertEquals("Test Hotel", persistedHotel.getName());
        assertEquals("Test Address", persistedHotel.getAddress());
        assertEquals(100, persistedHotel.getRooms());
    }

    @Test
    void testGetHotelById() {
        HotelDTO newHotel = new HotelDTO("Test Hotel", "Test Address", 100);
        HotelDTO persistedHotel = hotelDAO.create(newHotel);

        HotelDTO foundHotel = hotelDAO.getById(persistedHotel.getId());

        assertNotNull(foundHotel);
        assertEquals(persistedHotel.getId(), foundHotel.getId());
        assertEquals("Test Hotel", foundHotel.getName());
    }

    @Test
    void testGetAllHotels() {
        hotelDAO.create(new HotelDTO("Hotel 1", "Address 1", 10));
        hotelDAO.create(new HotelDTO("Hotel 2", "Address 2", 20));

        List<HotelDTO> hotels = hotelDAO.getAll();

        assertTrue(hotels.size() >= 2);
    }

    @Test
    void testUpdateHotel() {
        HotelDTO newHotel = new HotelDTO("Test Hotel", "Test Address", 100);
        HotelDTO persistedHotel = hotelDAO.create(newHotel);

        persistedHotel.setName("Updated Hotel");
        persistedHotel.setAddress("Updated Address");
        persistedHotel.setRooms(150);
        HotelDTO updatedHotel = hotelDAO.update(persistedHotel);

        assertEquals("Updated Hotel", updatedHotel.getName());
        assertEquals("Updated Address", updatedHotel.getAddress());
        assertEquals(150, updatedHotel.getRooms());
    }

    @Test
    void testDeleteHotel() {
        HotelDTO newHotel = new HotelDTO("Test Hotel", "Test Address", 100);
        HotelDTO persistedHotel = hotelDAO.create(newHotel);

        hotelDAO.delete(persistedHotel.getId());
        HotelDTO foundHotel = hotelDAO.getById(persistedHotel.getId());

        assertNull(foundHotel);
    }


}
