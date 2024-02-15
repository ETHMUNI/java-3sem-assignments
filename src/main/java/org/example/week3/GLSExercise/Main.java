package org.example.week3.GLSExercise;

import jakarta.persistence.EntityManager;
import org.example.week3.HibernateConfig;

public class Main {

    public static void main(String[] args) {

        EntityManager entityManager = HibernateConfig.buildEntityFactoryConfig().createEntityManager();
        entityManager.getTransaction().begin();

        PackageDAO packageDAO = new PackageDAO();

        GLSPackage GLSPackage1 = GLSPackage.builder()
                .trackingNumber("Q1W2E3R4")
                .senderName("John Doe")
                .receiverName("Bob Smith")
                .deliveryStatus(GLSPackage.DeliveryStatus.PENDING)
                .build();

        GLSPackage createdGLSPackage = packageDAO.create(GLSPackage1);

        GLSPackage readGLSPackage = packageDAO.read(1);
        System.out.println(readGLSPackage);

        createdGLSPackage.setDeliveryStatus(GLSPackage.DeliveryStatus.IN_TRANSIT);
        GLSPackage updatedGLSPackage = packageDAO.update(GLSPackage1);

        packageDAO.delete(1);
    }
}
