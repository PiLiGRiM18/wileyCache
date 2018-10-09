package com.company.caching;

import com.company.exceptions.NotFoundException;

import java.util.HashMap;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:28
 */
public class CacheMemory<K, V> implements Cache<K, V> {

    private HashMap<K, V> memotyCache = new HashMap<>();

    @Override
    public void put(K key, V value) {
        memotyCache.put(key, value);
    }

    @Override
    public V get(K key) throws NotFoundException {
        V value;
        try {
            value = memotyCache.get(key);
        } catch (Exception e) {
            throw new NotFoundException("Entity is not found");
        }
        return value;
    }

    @Override
    public V remove(K key) throws NotFoundException {
        try {
            memotyCache.remove(key);
        } catch (Exception e) {
            throw new NotFoundException("Entity is not found");
        }
        return null;
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
