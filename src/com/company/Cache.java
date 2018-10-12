package com.company;


import com.company.manager.CacheManager;
import com.company.manager.FilesystemManager;
import com.company.manager.MemoryManager;
import com.company.strategy.Strategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * @param <V>
 * @param <K>
 */
public class Cache<V, K> {

    private List<Measure<K>> measuresList = new ArrayList<>();
    private Map<K, Measure<K>> measuresMap = new HashMap<>();
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
            object = (V) serializer.deSerializeObject(new ByteArrayInputStream((byte[]) cacheManager.get(key)));
            if (cacheManager instanceof MemoryManager) System.out.println("object from RAM cache: ");
            if (cacheManager instanceof FilesystemManager) System.out.println("object from filesystem cache: ");
        } else {
            object = (V) dataObjectSource.getObject((Integer) key);
            System.out.println("object from source: ");
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
     * Remove object from cache by key
     */
    private void removeObject(K key) {
        cacheManager.remove(key);
        Measure measure = measuresMap.get(key);
        if (measure != null) {
            measuresList.remove(measure);
            measuresMap.remove(key);
        }
    }

    /**
     * Clear cache
     */
    public void clear() {
        cacheManager.clear();
        measuresMap.clear();
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
            measuresMap.remove(measuresList.get(0).key);
            measuresList.remove(0);
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        serializer.serializeObject(object, outputStream);
        cacheManager.put(key, outputStream.toByteArray());
    }

    /**
     * Performing current strategy to the object using current strategy
     */
    private void performStrategy(K key) {
        Measure measure = measuresMap.get(key);
        if (measure == null) {
            measure = new Measure<>(key);
            measuresMap.put(key, measure);
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

    @Override
    public String toString() {
        return "Cache{" +
                "\nmeasuresList=" + measuresList +
                ",\n measuresMap=" + measuresMap +
                ",\n strategy=" + strategy +
                ",\n serializer=" + serializer +
                ",\n maxCachedObjects=" + maxCachedObjects +
                ",\n cacheManager=" + cacheManager +
//                ",\n dataObjectSource=" + dataObjectSource +
                '}';
    }
}
