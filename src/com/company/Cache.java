package com.company;


import com.company.manager.CacheManager;
import com.company.strategy.Strategy;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @param <V>
 * @param <K>
 */
public class Cache<V, K> {

    private List<Measure<K>> measuresList = new ArrayList<>();
    private Strategy strategy;
    private Serializer serializer;
    private int maxCachedObjects;

    private CacheManager cacheManager;
    private DataObjectSource dataObjectSource;

    /**
     * @param cacheManager
     * @param strategy
     * @param dataObjectSource
     * @param serializer
     * @param maxCachedObjects
     */
    public Cache(CacheManager cacheManager,
                 Strategy strategy,
                 DataObjectSource dataObjectSource,
                 Serializer serializer,
                 int maxCachedObjects) {
        this.cacheManager = cacheManager;
        this.strategy = strategy;
        this.dataObjectSource = dataObjectSource;
        this.serializer = serializer;
        this.maxCachedObjects = maxCachedObjects;
    }

    /**
     * Get object from source by key
     * If object exists in cache, user get it from cache
     * If object is not exists in cache, user get it from source and save it in cache
     * After each method call the strategy performs to the cache
     */
    public V getObject(K key) {
        V object;

        if (cacheManager.contains(key)) {
            object = (V) cacheManager.get(key);
        } else {
            object = (V) dataObjectSource.getObject((Integer) key);
        }
        saveInCache(key, object);
        performStrategy(key);
        return object;
    }

    /**
     * Put object to cache by key
     */
    public void putObject(K key, V object) {
        if (cacheManager.contains(key)) {
            removeObject(key);
        }
        saveInCache(key, object);
        performStrategy(key);
    }

    /**
     * Remove object to cache by key
     */
    private void removeObject(K key) {
        cacheManager.remove(key);
        Measure measure = measuresList.get((Integer) key);
        if (measure != null) {
            measuresList.remove(measure);
        }
    }

    public void clear() {
        cacheManager.clear();
        measuresList.clear();
    }

    /**
     * Save object in cache
     * If cache is overloaded the object with bigges value of meter will be removed from cache
     * and the new object will be added in cache
     */
    private void saveInCache(K key, V object) {
        if (cacheManager.size() >= maxCachedObjects) {
            Collections.sort(measuresList, (Comparator<Measure>) (o1, o2) -> {
                if (o1.meter > o2.meter) {
                    return 1;
                } else if (o2.meter > o1.meter) {
                    return -1;
                } else {
                    return 0;
                }
            });
            cacheManager.remove(measuresList.get(0).key);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        serializer.serializeObject(object, outputStream);
        cacheManager.put(key, outputStream.toByteArray());
    }

    /**
     * Performing current strategy to the object using current strategy
     */
    private void performStrategy(K key) {
        Measure measure = measuresList.get((Integer) key);
        if (measure == null) {
            measure = new Measure<>(key);
            measuresList.add(measure);
        }
        measure.meter = strategy.changeMeter(measure.meter);
    }

    /**
     * The object contains information about some object located in cache
     */
    private static class Measure<T> {

        public T key;
        public long meter;

        public Measure(T key) {
            this.key = key;
        }
    }
}
