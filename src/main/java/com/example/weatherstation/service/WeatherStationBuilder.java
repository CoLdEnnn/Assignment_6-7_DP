package com.example.weatherstation.service;

import com.example.weatherstation.observer.WeatherObserver;
import com.example.weatherstation.strategy.UpdateStrategy;

public class WeatherStationBuilder {
    private final WeatherStation station = new WeatherStation();

    public WeatherStationBuilder addObserver(WeatherObserver observer) {
        if (observer != null) {
            station.addObserver(observer);
        }
        return this;
    }

    public WeatherStationBuilder setStrategy(UpdateStrategy strategy) {
        if (strategy != null) {
            station.setStrategy(strategy);
        }
        return this;
    }

    public WeatherStation build() {
        return station;
    }
}
