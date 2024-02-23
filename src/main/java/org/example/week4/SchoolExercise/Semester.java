package org.example.week4.SchoolExercise;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "semester")
@Getter
@Setter
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "semester_name")
    private String name;

    @Column(name = "semester_description")
    private String description;

    @OneToMany(mappedBy = "currentSemester")
    @Column(name = "student_id")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "teaches")
    @Column(name = "teacher_id")
    private List<Teacher> teachers = new ArrayList<>();
}
