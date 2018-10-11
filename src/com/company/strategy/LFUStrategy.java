package com.company.strategy;

public class LFUStrategy implements Strategy {
    @Override
    public long changeMeter(long nowMeter) {
        return nowMeter + 1;
    }
}
