package com.company;

import com.company.manager.MemoryManager;
import com.company.strategy.LRUStrategy;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Alexander Pervachuk <apervachuk@wiley.com> on 11.10.2018 12:09
 */
public class CacheTest {

    private DataObjectSource dataObjectSource;
    private Cache cache;

    @BeforeMethod
    public void precondition() {
        dataObjectSource = new DataObjectSource(100);
        cache = new Cache(
                new MemoryManager(),
                new LRUStrategy(),
                dataObjectSource,
                new Serializer(),
                10);
    }

    @Test
    public void testGetObject() {


        for (int i = 0; i < 100; i++) {
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