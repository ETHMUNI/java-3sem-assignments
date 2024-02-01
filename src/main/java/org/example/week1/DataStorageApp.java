package org.example.week1;

public class DataStorageApp {

    public static void main(String[] args) {
        DataStorage<String> memoryStorage = new MemoryStorage<>();
        memoryStorage.store("Hello, world!"); // Denne her har ID 1
        String retrievedString = memoryStorage.retrieve("1");

        System.out.println(retrievedString);

        DataStorage<Employee> fileStorage = new FileStorage<>();
        String filename = fileStorage.store(new Employee("John", 30));
        Employee retrievedInt = fileStorage.retrieve(filename);

        System.out.println(retrievedInt.getNames());
        System.out.println(retrievedInt.getAge());


    }
}
