package com.company;

import com.company.manager.CacheManager;
import com.company.strategy.Strategy;

public class Cache<V, K> {

    private CacheManager cacheManager;
    private Strategy strategy;
    private DataObjectSource dataObjectSource;
    private Serializer serializer;
    private int maxObjects;

    public Cache(CacheManager cacheManager, Strategy strategy, DataObjectSource dataObjectSource, Serializer serializer, int maxObjects) {
        this.cacheManager = cacheManager;
        this.strategy = strategy;
        this.dataObjectSource = dataObjectSource;
        this.serializer = serializer;
        this.maxObjects = maxObjects;
    }

    public V getObject(K key) {
        V object;

        if (cacheManager.contains(key)) {
            object = (V) cacheManager.get(key);
        } else {
            object = (V) dataObjectSource.getObject((Integer) key);
        }
        
        return object;
    }

    public void putObject(K key, V object) {
        if (cacheManager.contains(key)) {
            removeObject(key);
        }
        cacheObject(key, object);
        applyStrategy(key);
    }
}
