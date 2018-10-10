package com.company.strategy;

public class LFUStrategy implements Strategy {
    @Override
    public long checkObject(long now) {
        return now + 1;
    }
}
