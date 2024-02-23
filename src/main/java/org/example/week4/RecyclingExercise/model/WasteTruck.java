package org.example.week4.RecyclingExercise.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "truck")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class WasteTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;
    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_available")
    private boolean isAvailable;
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @ManyToOne
    private Driver driver;

    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }
}
