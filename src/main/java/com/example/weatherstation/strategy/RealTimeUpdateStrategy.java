package com.example.weatherstation.strategy;

import com.example.weatherstation.model.WeatherData;
import java.util.Random;

public class RealTimeUpdateStrategy implements UpdateStrategy {
    private final Random random = new Random();

    @Override
    public WeatherData update() {
        double temp = 15 + random.nextDouble() * 15;
        double humidity = 40 + random.nextDouble() * 40;
        double pressure = 1000 + random.nextDouble() * 20;
        System.out.println("\n[RealTimeUpdate] Weather updated.");
        return new WeatherData(temp, humidity, pressure);
    }
}
