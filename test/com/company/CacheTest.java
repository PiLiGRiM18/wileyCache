package com.company;

import com.company.manager.MemoryManager;
import com.company.strategy.LRUStrategy;
import org.testng.annotations.Test;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 11.10.2018 12:09
 */
public class CacheTest {

    @Test
    public void testGetObject() {

        DataObjectSource dataObjectSource = new DataObjectSource(100);

        Cache cache = new Cache(
                new MemoryManager(),
                new LRUStrategy(),
                dataObjectSource,
                new Serializer(),
                10);

        for (int i = 0; i < 1000; i++) {
            System.out.println(i + " : " + cache.getObject(dataObjectSource.getRandomKey()));
        }
    }

    @Test
    public void testPutObject() {
    }

    @Test
    public void testClear() {
    }
}