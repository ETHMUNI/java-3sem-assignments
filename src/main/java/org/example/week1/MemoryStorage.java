package org.example.week1;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage<T> implements DataStorage<T>{
    private Map<String, T> storage = new HashMap<>();
    private int idCounter = 0;
    @Override
    // Generere, gemmer og returnerer et id for data.
    public String store(T data) {
        String id = String.valueOf(++idCounter);
        storage.put(id, data);
        return id;
    }

    @Override
    // Henter data fra en source.
    public T retrieve(String source) {
        return storage.get(source);
    }
}
