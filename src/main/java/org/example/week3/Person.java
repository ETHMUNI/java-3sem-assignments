package org.example.week3;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "person")
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person(String firstName, String email, Gender gender) {
        this.firstName = firstName;
        this.email = email;
        this.gender = gender;
    }

    public enum Gender {
        MALE,
        FEMALE
    }
}
