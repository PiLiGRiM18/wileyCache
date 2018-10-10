package com.company;

import com.company.manager.CacheManager;
import com.company.strategy.Strategy;

public class Cache<V, K> {

    private CacheManager cacheManager;
    private Strategy strategy;
    private ObjectFactory objectFactory;
    private Serializer serializer;
    private int maxObjects;

    public Cache(CacheManager cacheManager, Strategy strategy, ObjectFactory objectFactory, Serializer serializer, int maxObjects) {
        this.cacheManager = cacheManager;
        this.strategy = strategy;
        this.objectFactory = objectFactory;
        this.serializer = serializer;
        this.maxObjects = maxObjects;
    }

    public V getObject(K key) {
        V object;


        return object;
    }
}
