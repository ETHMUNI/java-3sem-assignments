package org.example.week1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Employee implements Serializable {

    private String names;
    private int age;
    private int birthYear;
    private int birthMonth;
    private int birthDay;

    public Employee(String names) {
        this.names = names;
    }

    public Employee(String names, int birthYear, int birthMonth, int birthDay) {
        this.names = names;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    public Employee(String names, int age) {
        this.names = names;
        this.age = age;
    }

    public String getNames() {
        return names;
    }

    public int getAge() {
        return age;
    }


    public int calculateYear(int currentYear) {
        return currentYear - birthYear;
    }
}
