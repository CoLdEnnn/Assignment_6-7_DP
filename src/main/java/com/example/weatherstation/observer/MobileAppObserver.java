package com.example.weatherstation.observer;

import com.example.weatherstation.model.WeatherData;

public class MobileAppObserver implements WeatherObserver {
    private final String name;

    public MobileAppObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(WeatherData data) {
        System.out.println("[MobileApp " + name + "] -> New Weather: " + data);
    }

    @Override
    public String getName() {
        return name;
    }
}
