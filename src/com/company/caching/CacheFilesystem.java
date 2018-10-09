package com.company.caching;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:29
 */
public class CacheFilesystem<K, V> implements Cache<K, V> {

    private static final String DEFAULT_DIR_PATH = "";
    private static final String DIR_PATH = "";

    public CacheFilesystem() {
        this(DEFAULT_DIR_PATH);
    }

    public CacheFilesystem(String dirPath) {
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
