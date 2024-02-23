package org.example.week3.GLSExercise;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private int id;
    @ManyToOne
    private GLSPackage GLSpackage;
    @ManyToOne
    private Location sourceLocation;
    @Column(name = "shipment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;

    @ManyToOne
    private Location destinationLocation;
}
