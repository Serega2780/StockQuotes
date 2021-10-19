package com.price.processor;

import com.price.processor.model.Instrument;
import com.price.processor.utils.PriceGenerator;
import java.util.stream.Stream;

public class StockStream {
    private final static int ARRAY_SIZE = 1_000;
    private final static Instrument[] instruments = new Instrument[ARRAY_SIZE];

    public StockStream() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            instruments[i] = PriceGenerator.ccyPairCreate();
        }
    }

    public Stream<Instrument> getInstrument() {
        return Stream.of(instruments);
    }

    public String[] getCcyPairs(){
        return PriceGenerator.getCcyPairs();
    }
}
