package com.example.weatherstation.strategy;

import com.example.weatherstation.model.WeatherData;
import java.util.Random;

public class BatchUpdateStrategy implements UpdateStrategy {
    private final Random random = new Random();

    @Override
    public WeatherData update() {
        double temp = 10 + random.nextDouble() * 20;
        double humidity = 30 + random.nextDouble() * 50;
        double pressure = 990 + random.nextDouble() * 25;
        System.out.println("\n[BatchUpdate] Weather updated.");
        return new WeatherData(temp, humidity, pressure);
    }
}
