package com.example.weatherstation.factory;

import com.example.weatherstation.observer.DisplayPanelObserver;
import com.example.weatherstation.observer.MobileAppObserver;
import com.example.weatherstation.observer.WeatherObserver;

public final class ObserverFactory {
    private ObserverFactory() {}
    public static WeatherObserver create(String type, String name) {
        if (type == null || name == null) {
            throw new IllegalArgumentException("Observer type and name cannot be null!");
        }

        return switch (type.toLowerCase()) {
            case "mobile" -> new MobileAppObserver(name);
            case "display" -> new DisplayPanelObserver(name);
            default -> throw new IllegalArgumentException("Unknown observer type: " + type);
        };
    }
}