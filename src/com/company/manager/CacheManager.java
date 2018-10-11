package com.company.manager;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 10:15
 */
public interface CacheManager<K, V> {
    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean contains(K key);

    void clear();

    int size();
}
