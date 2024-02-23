package org.example.week4.RecyclingExercise.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Builder
@Entity
@Table(name = "driver")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "employment_date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private WasteTruck wasteTruck;

    public Driver(String name, BigDecimal salary, String surname) {
        this.name = name;
        this.salary = salary;
        this.surname = surname;
    }

    public String generateId() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        String date = sdf.format(this.date);
        String initials = this.name.substring(0, 1) + this.surname.substring(0, 1);

        Random random = new Random();
        int number = random.nextInt(900) + 100;

        char lastLetter = surname.charAt(surname.length() - 1);

        id = date + initials + number + lastLetter;

        return id;
    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
        this.id = generateId();
    }
}
