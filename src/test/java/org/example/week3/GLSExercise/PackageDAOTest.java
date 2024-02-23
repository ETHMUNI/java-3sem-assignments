package org.example.week3.GLSExercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.week3.HibernateConfig;
import org.example.week3.PointExercise.QueryDAO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PackageDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PackageDAO packageDAO;
    @BeforeAll
    public static void setUp() {

        emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        packageDAO = new PackageDAO();
    }

    @AfterAll
    public static void tearDown() {
        em.close();
    }


    @Test
    public void create() {
        GLSPackage pkg = new GLSPackage();
        pkg.setTrackingNumber("12345");
        pkg.setSenderName("Jane");
        pkg.setReceiverName("John");
        pkg.setDeliveryStatus(GLSPackage.DeliveryStatus.PENDING);

        packageDAO.create(pkg);

        // Retrieve the package from the database and assert its existence
        GLSPackage retrievedPackage = em.find(GLSPackage.class, pkg.getId());
        Assertions.assertNotNull(retrievedPackage);
        Assertions.assertEquals("12345", retrievedPackage.getTrackingNumber());
    }

    @Test
    public void read() {

        GLSPackage createdPackage = new GLSPackage();
        createdPackage.setTrackingNumber("READ123");
        createdPackage.setSenderName("John");
        createdPackage.setReceiverName("Bob");
        createdPackage.setDeliveryStatus(GLSPackage.DeliveryStatus.PENDING);
        packageDAO.create(createdPackage);

        // Now attempt to read this package back from the database
        GLSPackage retrievedPackage = packageDAO.read(createdPackage.getId());
        Assertions.assertNotNull(retrievedPackage);
        Assertions.assertEquals("READ123", retrievedPackage.getTrackingNumber());
    }

    @Test
    public void update() {
        // Create a package to update
        GLSPackage pkgToUpdate = new GLSPackage();
        pkgToUpdate.setTrackingNumber("UPD123");
        pkgToUpdate.setSenderName("Bob");
        pkgToUpdate.setReceiverName("John");
        pkgToUpdate.setDeliveryStatus(GLSPackage.DeliveryStatus.PENDING);
        packageDAO.create(pkgToUpdate);

        // Update some details
        pkgToUpdate.setReceiverName("Hans");
        pkgToUpdate.setDeliveryStatus(GLSPackage.DeliveryStatus.DELIVERED);
        packageDAO.update(pkgToUpdate);

        // Retrieve the updated package
        GLSPackage updatedPackage = em.find(GLSPackage.class, pkgToUpdate.getId());
        Assertions.assertEquals("Hans", updatedPackage.getReceiverName());
        Assertions.assertEquals(GLSPackage.DeliveryStatus.DELIVERED, updatedPackage.getDeliveryStatus());
    }

    @Test
    public void delete() {
        GLSPackage pkgToDelete = new GLSPackage();
        pkgToDelete.setTrackingNumber("DEL123");
        pkgToDelete.setSenderName("John Doe");
        pkgToDelete.setReceiverName("Bob");
        pkgToDelete.setDeliveryStatus(GLSPackage.DeliveryStatus.PENDING);
        packageDAO.create(pkgToDelete);

        // Delete the package
        packageDAO.delete(pkgToDelete.getId());

        // Attempt to retrieve the deleted package
        GLSPackage deletedPackage = em.find(GLSPackage.class, pkgToDelete.getId());
        Assertions.assertNull(deletedPackage);
    }
}