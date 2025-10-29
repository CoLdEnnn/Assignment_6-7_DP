package com.example.weatherstation.controller;

import com.example.weatherstation.service.WeatherStation;
import com.example.weatherstation.strategy.BatchUpdateStrategy;
import com.example.weatherstation.strategy.ManualUpdateStrategy;
import com.example.weatherstation.strategy.RealTimeUpdateStrategy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class WeatherController {
    private final WeatherStation station;

    public WeatherController(WeatherStation station) {
        this.station = station;
    }

    @GetMapping("/strategy/{type}")
    public String changeStrategy(@PathVariable String type) {
        if (type == null) return "[Error] type required";

        switch (type.toLowerCase()) {
            case "realtime" -> station.setStrategy(new RealTimeUpdateStrategy());
            case "batch" -> station.setStrategy(new BatchUpdateStrategy());
            case "manual" -> station.setStrategy(new ManualUpdateStrategy());
            default -> {
                return "[Error] Unknown strategy: " + type;
            }
        }
        return "[Strategy] Changed to: " + station.getCurrentStrategyName();
    }

    @GetMapping("/add-observer/{type}/{name}")
    public String addObserver(@PathVariable String type, @PathVariable String name) {
        return station.addObserver(type, name);
    }

    @RequestMapping(value = "/remove-observer/{name}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeObserver(@PathVariable String name) {
        return station.removeObserver(name);
    }

    @GetMapping("/observers")
    public String listObservers() {
        var list = station.listObserverNames();
        if (list.isEmpty()) return "[Info] No active observers.";
        return String.join("\n", list);
    }

    @GetMapping("/update")
    public String updateWeather() {
        station.updateWeather();
        return "[Weather] Updated (strategy: " + station.getCurrentStrategyName() + ")";
    }
}
