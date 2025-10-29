package com.example.weatherstation.model;

import java.time.LocalDateTime;

public class WeatherData {
    private double temperature;
    private double humidity;
    private double pressure;
    private LocalDateTime timestamp;

    public WeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.timestamp = LocalDateTime.now();
    }

    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public double getPressure() { return pressure; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Temperature: %.1f°C, Humidity: %.1f%%, Pressure: %.1fhPa (%s)",
                temperature, humidity, pressure, timestamp.toLocalTime());
    }
}
