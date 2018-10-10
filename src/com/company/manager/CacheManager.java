package com.company.manager;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 10:15
 */
public interface CacheManager<K, V> {
    void put(K key, V value);

    V get(K key) throws Exception;

    V remove(K key) throws Exception;

    void clear();

    int size();
}
