package com.example.weatherstation.service;

import com.example.weatherstation.factory.ObserverFactory;
import com.example.weatherstation.model.WeatherData;
import com.example.weatherstation.observer.WeatherObserver;
import com.example.weatherstation.strategy.UpdateStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherStation {
    private UpdateStrategy strategy;
    private final List<WeatherObserver> observers = new ArrayList<>();

    public void setStrategy(UpdateStrategy strategy) {
        this.strategy = strategy;
        System.out.println("Strategy changed to: " + (strategy == null ? "null" : strategy.getClass().getSimpleName()));
    }

    public String getCurrentStrategyName() {
        return (strategy == null) ? "No strategy selected" : strategy.getClass().getSimpleName();
    }

    public String addObserver(WeatherObserver observer) {
        if (observer == null) {
            return "Invalid observer (null).";
        }
        String name = normalizeName(observer.getName());
        if (name.isEmpty()) {
            return "Invalid observer: name is required.";
        }

        boolean exists = observers.stream()
                .anyMatch(o -> o.getName().equalsIgnoreCase(name));
        if (exists) {
            return "Observer with name '" + name + "' already exists!";
        }
        observers.add(observer);
        System.out.println("Added observer: " + name);
        return "Added new observer: " + name;
    }

    public String addObserver(String type, String name) {
        if (type == null || name == null) {
            return "Type and name are required.";
        }
        String n = normalizeName(name);
        if (n.isEmpty()) return "Invalid observer name.";

        WeatherObserver observer = ObserverFactory.create(type, n);
        if (observer == null) {
            return "Unknown observer type: " + type;
        }
        return addObserver(observer);
    }

    public String removeObserver(String name) {
        String n = normalizeName(name);
        if (n.isEmpty()) return "Name is required.";

        boolean removed = observers.removeIf(o -> o.getName().equalsIgnoreCase(n));
        return removed ? "Removed observer: " + n : "No observer found with name: " + n;
    }

    public List<String> listObserverNames() {
        if (observers.isEmpty()) {
            return List.of();
        }
        return observers.stream()
                .map(WeatherObserver::getName)
                .toList();
    }

    public void updateWeather() {
        if (strategy == null) {
            System.out.println("[WeatherStation] No strategy selected!");
            return;
        }
        WeatherData data = strategy.update();
        notifyObservers(data);
    }

    private void notifyObservers(WeatherData data) {
        for (WeatherObserver observer : observers) {
            if (observer != null) {
                try {
                    observer.update(data);
                } catch (Exception ex) {
                    System.out.println("Error notifying observer " + (observer.getName()) + ": " + ex.getMessage());
                }
            }
        }
    }

    private String normalizeName(String name) {
        return (name == null) ? "" : name.trim();
    }
}
