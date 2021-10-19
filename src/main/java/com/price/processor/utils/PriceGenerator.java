package com.price.processor.utils;

import com.price.processor.model.Instrument;

import java.time.LocalTime;

public class PriceGenerator {
    private final static MyStock myStock = new MyStock();

    public static Instrument ccyPairCreate() {
        return Instrument.of(myStock.ccyPair(), Math.random(), LocalTime.now());
    }

    public static String[] getCcyPairs() {
        return myStock.getSTOCK_ARRAY();
    }

}
