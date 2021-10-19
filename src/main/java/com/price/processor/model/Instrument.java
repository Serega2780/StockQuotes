package com.price.processor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@AllArgsConstructor(staticName ="of")
@Getter
public class Instrument {
    private String ccyPair;
    private double rate;
    private LocalTime stamp;
}
