package org.example.week1;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage<T> implements DataStorage<T>{
    private Map<String, T> storage = new HashMap<>();
    private int idCounter = 0;
    @Override
    public String store(T data) {
        String id = String.valueOf(++idCounter);
        storage.put(id, data);
        return id;
    }

    @Override
    public T retrieve(String id) {
        return storage.get(id);
    }
}
