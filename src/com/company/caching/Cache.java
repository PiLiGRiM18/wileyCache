package com.company.caching;

import com.company.exceptions.NotFoundException;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 09.10.2018 10:15
 */
public interface Cache<K, V> {
    void put(K key, V value);

    V get(K key) throws NotFoundException;

    V remove(K key) throws NotFoundException;

    void clear();

    int size();
}
