package com.neha.kvstore.node;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KeyValueStore {
    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        store.put(key, value);
    }

    public String get(String key){
        return store.get(key);
    }

    public boolean delete(String key){
        return store.remove(key) != null; //returns the previous value associated with the key
    }

    public int size(){
        return store.size();
    }
}
