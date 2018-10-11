package com.company.manager;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:29
 */
public class FilesystemManager<K, V> implements CacheManager<K, V> {

    private static final String DEFAULT_DIR_PATH = "";
    private static final String DIR_PATH = "";


    public FilesystemManager() {
        this(DEFAULT_DIR_PATH);
    }

    public FilesystemManager(String dirPath) {
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
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
