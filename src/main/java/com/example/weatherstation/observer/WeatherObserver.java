package com.example.weatherstation.observer;

import com.example.weatherstation.model.WeatherData;

public interface WeatherObserver {
    void update(WeatherData data);
    String getName();
}
