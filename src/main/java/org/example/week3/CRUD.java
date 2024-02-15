package org.example.week3;

public interface CRUD<T> {
    T create(T t);
    T read(int id);
    T update(T t);

    void delete(int id);
}
