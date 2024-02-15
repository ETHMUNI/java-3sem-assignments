package org.example.week3.UnicornExercise;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "unicorn")
@NoArgsConstructor
@ToString
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "power_strength")
    @Enumerated(EnumType.STRING)
    PowerStrength powerStrength;

    public Unicorn(String name, int age, PowerStrength powerStrength) {
        this.name = name;
        this.age = age;
        this.powerStrength = powerStrength;
    }

    public enum PowerStrength {
        FIRE,
        WATER,
        EARTH,
        AIR
    }
}
