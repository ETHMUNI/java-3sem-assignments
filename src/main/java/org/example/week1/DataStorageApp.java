package org.example.week1;

public class DataStorageApp {

    public static void main(String[] args) {
        DataStorage<String> memoryStorage = new MemoryStorage<>();
        memoryStorage.store("Hello, world!");
        String retrievedString = memoryStorage.retrieve(null);

        DataStorage<Employee> fileStorage = new FileStorage<>();
        String filename = fileStorage.store(new Employee("John", 30));
        Employee retrievedInt = fileStorage.retrieve(filename);
    }
}
