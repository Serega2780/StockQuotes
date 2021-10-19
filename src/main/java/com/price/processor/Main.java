package com.price.processor;

import com.price.processor.model.Instrument;
import com.price.processor.utils.DefaultSubscriber;
import org.reactivestreams.Subscriber;

import java.time.Duration;

public class Main {
    private final static PriceProcessor priceProcessor = new PriceProcessorImpl();

    public static void main(String[] args) throws InterruptedException {

        Subscriber<Instrument> subscriber1 = new DefaultSubscriber("subscriber1", 100);
        Subscriber<Instrument> subscriber2 = new DefaultSubscriber("subscriber2", 300);
        Subscriber<Instrument> subscriber3 = new DefaultSubscriber("subscriber3", 500);
        Subscriber<Instrument> subscriber4 = new DefaultSubscriber("subscriber4", 1000);
        Subscriber<Instrument> subscriber5 = new DefaultSubscriber("subscriber5", 1500);



        priceProcessor.subscribe(subscriber1);
        Thread.sleep(Duration.ofMillis(10).toMillis());

        priceProcessor.subscribe(subscriber2);
        Thread.sleep(Duration.ofMillis(20).toMillis());

        priceProcessor.subscribe(subscriber3);
        Thread.sleep(Duration.ofMillis(30).toMillis());

        priceProcessor.subscribe(subscriber4);
        Thread.sleep(Duration.ofMillis(40).toMillis());

        priceProcessor.subscribe(subscriber5);

        Thread.sleep(Duration.ofSeconds(60).toMillis());
    }

}
