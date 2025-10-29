package com.example.weatherstation.strategy;

import com.example.weatherstation.model.WeatherData;

public class ManualUpdateStrategy implements UpdateStrategy {
    @Override
    public WeatherData update() {
        System.out.println("\n[ManualUpdate] Weather updated.");
        return new WeatherData(25.0, 50.0, 1013.0);
    }
}
