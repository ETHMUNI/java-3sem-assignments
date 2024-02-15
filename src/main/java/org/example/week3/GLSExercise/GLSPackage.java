package org.example.week3.GLSExercise;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Entity
@Table(name = "gls_packages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GLSPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trackingNumber", unique = true)
    private String trackingNumber;

    @Column(name = "senderName")
    private String senderName;

    @Column(name = "receiverName")
    private String receiverName;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryStatus")
    private DeliveryStatus deliveryStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public enum DeliveryStatus {
        PENDING,
        DELIVERED,
        IN_TRANSIT,
    }

   @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Set to current time when persisting
        this.updatedAt = LocalDateTime.now(); // Set to current time as well
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now(); // Update with current time when updating the entity
    }

}
