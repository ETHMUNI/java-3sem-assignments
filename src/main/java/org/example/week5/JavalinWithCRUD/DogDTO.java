package org.example.week5.JavalinWithCRUD;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class DogDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String breed;
    @NonNull
    @Enumerated(EnumType.STRING)
    Gender gender;
    @NonNull
    private int age;

    public void setId(UUID id) {
    }

    public enum Gender {
        MALE,
        FEMALE,
    }
}

