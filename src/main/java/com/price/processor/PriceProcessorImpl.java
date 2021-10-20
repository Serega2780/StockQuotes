package com.price.processor;

import com.price.processor.model.Instrument;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


public class PriceProcessorImpl implements PriceProcessor {

    private final StockStream stockStream = new StockStream();
    private final Flux<Instrument> mainStream;

    public PriceProcessorImpl() {
// "hot" flux
        this.mainStream = Flux.create((FluxSink<Instrument> fluxSink) -> stockStream.getInstrument().forEach(fluxSink::next))
                .delayElements(Duration.ofMillis(0))
                .share()
                .cache(0);
    }

    //не понятно, какой функционал предполагался
    @Override
    public void onPrice(String ccyPair, double rate) {

    }

    //в задании subscribe и unsubscribe получают на вход PriceProcessor. Не придумал, как бы это работало.
    @Override
    public void subscribe(Subscriber<Instrument> subscriber) {
        Flux.merge(mainStreamRefactor(mainStream))
                .distinct()
                .publishOn(Schedulers.boundedElastic())
                .subscribe(subscriber);

    }

    @Override
    public void unsubscribe(Subscriber<Instrument> subscriber) {
        subscriber.onComplete();
    }

    //разбираем общий поток котировок на массив потоков (split by ccyPair), чтобы повесить onBackpressureLatest()
    //на каждую пару.
    private Flux<Instrument>[] mainStreamRefactor(Flux<Instrument> mainStream) {

        Flux<Instrument>[] fluxArray = new Flux[stockStream.getCcyPairs().length];
        int i = 0;
        for (String ccyPair : stockStream.getCcyPairs()
        ) {
            fluxArray[i] = mainStream
                    .filter(e -> e.getCcyPair().equals(ccyPair))
                    .onBackpressureLatest();
            i++;
        }
        return fluxArray;
    }
}
