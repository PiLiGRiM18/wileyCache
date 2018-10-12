package com.company.manager;

import java.io.IOException;
import java.nio.file.Files;
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
        if (contains(key)) {
            try {
                dataObject = (V) Files.readAllBytes(getFileBytes(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataObject;
    }

    @Override
    public void put(K key, V value) {
        try {
            Files.write(getFileBytes(key), (byte[]) value);
            keys.put(key, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(K key) {
        try {
            Files.delete(getFileBytes(key));
            keys.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean contains(K key) {
        return keys.containsKey(key);
    }

    @Override
    public void clear() {
        keys.clear();
    }

    @Override
    public int size() {
        return keys.size();
    }

    private Path getFileBytes(K key) {
        return localDirectory.resolve(key.toString());
    }
}
