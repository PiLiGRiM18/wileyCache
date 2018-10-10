package com.company.strategy;

public class LRUStrategy implements Strategy {
    @Override
    public long checkObject(long now) {
        return System.currentTimeMillis();
    }
}
