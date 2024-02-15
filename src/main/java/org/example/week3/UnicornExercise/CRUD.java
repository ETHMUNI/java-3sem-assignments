package org.example.week3.UnicornExercise;

public interface CRUD<T, U> {

    T save(U u);
    T findById(int id);
    T update(U u);
    void delete(int id);
}
