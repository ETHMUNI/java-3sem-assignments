package org.example.week3.Lombok;

public class Main {
        public static void main(String[] args) {
            Person person = Person.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .age(25)
                    .build();
            System.out.println(person); // This should print something like "Person(firstName=John, lastName=Doe, age=25)"

            person.setAge(26);
            System.out.println(person.getAge()); // This should print "26"
        }
}
