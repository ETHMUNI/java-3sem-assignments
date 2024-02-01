package org.example.week1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalIF {



    public static void main(String[] args) {

        // Predicate
        Predicate<Integer> divisibleBy3 = x -> x % 3 == 0;
        System.out.println(divisibleBy3.test(3)); // true
        System.out.println(divisibleBy3.test(4)); // false

        //Supplier
        Supplier<Employee> employees = () -> {
            List<String> employeeNames = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");

            return new Employee(employeeNames.toString());
        };
        //Employee employee = employees.get();
        //System.out.println(employee.getNames());

        // Consumer
        Consumer<Employee> printElements = employee -> System.out.println(employee.getNames());

        Employee employee = employees.get();
        printElements.accept(employee); // accept() is a method in Consumer interface. Even tho Consumer returns void, you determind which kind of objects Consumer can accept and work with.

        // Function
        Function<Employee, String> objToString = obj -> obj.getNames();
        System.out.println(objToString.apply(employee));

        //Predicate - Check if an employee is older than 18
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Joe", "Jill");
        List<Integer> age = Arrays.asList(19, 44, 17, 27, 16);

        Predicate<Integer> isOver = num -> num > 18;


        // Prints all the names and age of those that is above 18 and leaves out those who are 18 and under.
        for(int i = 0; i < names.size(); i++) {
            if(isOver.test(age.get(i))) {
                System.out.println(names.get(i) + ", " + age.get(i));
            }
        }



    }
}
