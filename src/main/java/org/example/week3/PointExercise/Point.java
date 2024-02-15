package org.example.week3.PointExercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "point")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
