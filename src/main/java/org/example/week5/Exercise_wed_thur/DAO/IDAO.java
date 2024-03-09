package org.example.week5.Exercise_wed_thur.DAO;

import java.util.List;

public interface IDAO<T, V> {

    List<T> getAll(V v);

    T getById(int id);

    T create(V v);

    T update(V v);

    void delete(int id);
}
