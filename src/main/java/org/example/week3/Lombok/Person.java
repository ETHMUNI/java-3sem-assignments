package org.example.week3.Lombok;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
