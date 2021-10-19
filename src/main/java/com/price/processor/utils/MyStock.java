package com.price.processor.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class MyStock {
    private static final int ARRAY_SIZE = 1_000;
    private final String[] STOCK_ARRAY = new String[ARRAY_SIZE];
    private final AtomicInteger COUNTER = new AtomicInteger(1);
    private final AtomicInteger OUTER_COUNTER = new AtomicInteger(0);

    public MyStock(){
        for (int i = 1; i <= ARRAY_SIZE; i++) {
            if (COUNTER.get() == 201) COUNTER.set(1);
            STOCK_ARRAY[i-1] = "String № " + COUNTER.getAndIncrement();
        }
    }

    public String ccyPair() {
        return STOCK_ARRAY[OUTER_COUNTER.getAndIncrement()];
    }

    public String[] getSTOCK_ARRAY(){
        return this.STOCK_ARRAY;
    }

}
