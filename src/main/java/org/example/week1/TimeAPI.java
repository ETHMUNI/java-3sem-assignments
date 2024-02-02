package org.example.week1;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class TimeAPI {

        public static void main(String[] args) {

            // 1. Calculate the age of each employee based on their birthdate
            int currentYear = Year.now().getValue();
            List<Employee> employees = new ArrayList<>();

            employees.add(new Employee("John", 1998, 4,5));
            employees.add(new Employee("Jane", 2000, 2, 3));
            employees.add(new Employee("Jack", 1988, 1, 2));
            employees.add(new Employee("Joe", 1999,3, 19));
            employees.add(new Employee("Jill", 1995,11,20));

            for (Employee employee : employees) {
                System.out.println(employee.getNames() + ", " + employee.calculateYear(currentYear));
            }

            // 2. Calculate the average age of all employees

            int totalAge = 0;
            for (Employee employee : employees) {
                totalAge += employee.calculateYear(currentYear);
            }
            System.out.println("Average age: " + totalAge / employees.size());


            // 3. Filter and display employees who have birthdays in a specific month
            // use Predicate

            // 4. Group employees by birth month and display the count of employees in each group.

            // 5. List all employees who has a birthday in the current month.
        }

}
