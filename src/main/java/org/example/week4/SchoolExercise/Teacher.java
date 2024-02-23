package org.example.week4.SchoolExercise;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "teacher_semester",
            joinColumns = @JoinColumn(name = "teacher_id"), // Navnet på kolonnen i denne entitet aka. ejer-siden
            inverseJoinColumns = @JoinColumn(name = "semester_id") // Navnet på kolonnen i den modsatte entitet
    )
    private List<Semester> teaches = new ArrayList<>();
}
