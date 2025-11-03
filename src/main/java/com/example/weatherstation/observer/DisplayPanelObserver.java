package com.example.weatherstation.observer;

import com.example.weatherstation.model.WeatherData;

public class DisplayPanelObserver implements WeatherObserver {
    private final String id;

    public DisplayPanelObserver(String id) {
        this.id = id;
    }

    @Override
    public void update(WeatherData data) {
        System.out.println("[DisplayPanel #" + id + "]  Updated: " + data);
    }

    @Override
    public String getName() {
        return id;
    }
}
