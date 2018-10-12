package com.company.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:28
 */
public class MemoryManager<K, V> implements CacheManager<K, V> {

    private Map<K, V> memoryCache = new HashMap<>();

    @Override
    public void put(K key, V value) {
        memoryCache.put(key, value);
    }

    @Override
    public V get(K key) {
        V dataObject;
        try {
            dataObject = memoryCache.get(key);
        } catch (Exception e) {
            System.out.println("Entity is not found");
            dataObject = null;
        }
        return dataObject;
    }

    @Override
    public void remove(K key) {
        try {
            memoryCache.remove(key);
        } catch (Exception e) {
            System.out.println("Entity is not found");
        }
    }

    @Override
    public boolean contains(K key) {
        return memoryCache.containsKey(key);
    }

    @Override
    public void clear() {
        memoryCache.clear();
    }

    @Override
    public int size() {
        return memoryCache.size();
    }
}
