package com.company;

import com.company.manager.CacheManager;
import com.company.manager.FilesystemManager;
import com.company.manager.MemoryManager;
import com.company.strategy.LRUStrategy;
import com.company.strategy.Strategy;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 11.10.2018 12:09
 */
public class CacheTest {

    private static final int NUMBER_SOURCE_OBJECTS = 10;
    private static final int MAX_CACHE_SIZE = 10;

    private DataObjectSource dataObjectSource;
    private CacheManager cacheManager;
    private Strategy strategy;
    private Serializer serializer;
    private Cache cache;

    @BeforeMethod
    public void precondition() {
        dataObjectSource = new DataObjectSource(NUMBER_SOURCE_OBJECTS);
        serializer = new Serializer();

        cacheManager = new FilesystemManager();
//        cacheManager = new MemoryManager();

        strategy = new LRUStrategy();
//        strategy = new LFUStrategy();

        cache = new Cache(cacheManager, strategy, dataObjectSource, serializer, MAX_CACHE_SIZE);
    }

    @Test
    public void testGetObject() {

        final int ITERATIONS = 100;

        for (int i = 0; i < ITERATIONS; i++) {
            int key = dataObjectSource.getRandomKey();
            System.out.print("Request for object: " + key + ", ");
            DataObject dataObject = (DataObject) cache.getObject(key);
            Assertions.assertEquals(key, dataObject.getKey());
            System.out.println("Iteration:" + i + " | " + dataObject + "\n");
        }
    }

    @Test
    public void testPutObject() {
    }

    @Test
    public void testClear() {
        DataObjectSource dataObjectSource = new DataObjectSource(100);

        Cache cache = new Cache(
                new MemoryManager(),
                new LRUStrategy(),
                dataObjectSource,
                new Serializer(),
                10);
    }
}