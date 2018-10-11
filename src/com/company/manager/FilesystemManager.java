package com.company.manager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 11:29
 */
public class FilesystemManager<K, V> implements CacheManager<K, V> {

    private Path localDirectory;
    private Map keys = new HashMap();

    public FilesystemManager() {
        this("local_cache");
    }

    public FilesystemManager(String dirPath) {
        this.localDirectory = Paths.get(dirPath);
    }

    @Override
    public V get(K key) {
        V dataObject = null;

        if (keys.containsKey(key)) {

        }
        return dataObject;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public void remove(K key) {
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
