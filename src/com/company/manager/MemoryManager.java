package com.company.manager;

import java.util.HashMap;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:28
 */
public class MemoryManager<K, V> implements CacheManager<K, V> {

    private HashMap<K, V> memotyCache = new HashMap<>();

    @Override
    public void put(K key, V value) {
        memotyCache.put(key, value);
    }

    @Override
    public V get(K key) {
        V dataObject;
        try {
            dataObject = memotyCache.get(key);
        } catch (Exception e) {
            System.out.println("Entity is not found");
            dataObject = null;
        }
        return dataObject;
    }

    @Override
    public void remove(K key) {
        try {
            memotyCache.remove(key);
        } catch (Exception e) {
            System.out.println("Entity is not found");
        }
    }

    @Override
    public boolean contains(K key) {
        return memotyCache.containsKey(key);
    }

    @Override
    public void clear() {
        memotyCache.clear();
    }

    @Override
    public int size() {
        return memotyCache.size();
    }
}
