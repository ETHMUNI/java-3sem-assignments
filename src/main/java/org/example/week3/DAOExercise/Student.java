package org.example.week3.DAOExercise;

import jakarta.persistence.*;
import lombok.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "major")
    private String major;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

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
