package com.company;

import com.company.manager.MemoryManager;
import com.company.strategy.LFUStrategy;
import com.company.strategy.LRUStrategy;

public class Main {

    private static final int MAX_OBJECTS = 100;

    private static MemoryManager memoryManager;
    private static ObjectFactory objectFactory;
    private static Cache LFUcache;
    private static Cache LRUcache;

    public static void main(String[] args) {
        memoryManager = new MemoryManager();
        objectFactory = new ObjectFactory(100);
        LFUcache = new Cache(memoryManager, new LFUStrategy(), objectFactory, new Serializer(), MAX_OBJECTS);
        LFUcache = new Cache(memoryManager, new LRUStrategy(), objectFactory, new Serializer(), MAX_OBJECTS);
    }
}
