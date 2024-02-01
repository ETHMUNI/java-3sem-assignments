package org.example.week1;

public interface DataStorage <T> {
    String store(T data);
    T retrieve(String id);
}
