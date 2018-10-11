package com.company;

import com.company.manager.CacheManager;
import com.company.manager.FilesystemManager;
import com.company.manager.MemoryManager;
import com.company.strategy.LRUStrategy;
import org.testng.Assert;
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
        CacheManager memoryCacheManager = new MemoryManager();
        CacheManager fileSystemCacheManager = new FilesystemManager();

        cache = new Cache(
                fileSystemCacheManager,
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
            Assert.assertEquals(key, dataObject.getKey());
            System.out.println("Iteration:" + i + " | " + dataObject + "\n");
        }
    }

    @Test
    public void testPutObject() {
        int key = dataObjectSource.getRandomKey();
        for (int i = 0; i < 100; i++) {
            cache.putObject(key, dataObjectSource.getObject(key));
        }
        System.out.println(cache);
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 100; i++) {
            int key = dataObjectSource.getRandomKey();
            cache.getObject(key);
        }
        System.out.println("\nBefore clearing: \n");
        System.out.println(cache);
        cache.clear();
        System.out.println("\nAfter clearing: \n");
        System.out.println(cache);
    }
}