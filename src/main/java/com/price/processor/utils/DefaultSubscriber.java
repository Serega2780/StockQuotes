package com.price.processor.utils;

import com.price.processor.model.Instrument;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;


public class DefaultSubscriber implements Subscriber<Instrument> {

    private final String name;
    private final long jobDelay;

    public DefaultSubscriber(String name, long jobDelay) {
        this.name = name;
        this.jobDelay = jobDelay;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Instrument instrument) {
        Instant instant = Instant.now();
        LocalTime time = instant.atZone(ZoneOffset.UTC).toLocalTime();
        System.out.println(time + ", " + name + ", " + " Received in " + Thread.currentThread().getName() + ", " + "ccyPair: " + instrument.getCcyPair() + ", rate: " + instrument.getRate() + ", stamp: " + instrument.getStamp());

        try {
            Thread.sleep(jobDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + " ERROR : " + throwable.getMessage());
    }


    @Override
    public void onComplete() {
        System.out.println(name + " Completed");
    }
}
