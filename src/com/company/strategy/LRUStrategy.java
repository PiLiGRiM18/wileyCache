package com.company.strategy;

public class LRUStrategy implements Strategy {
    @Override
    public long changeMeter(long nowMeter) {
        return System.currentTimeMillis();
    }
}
