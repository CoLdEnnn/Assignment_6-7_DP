package com.example.weatherstation;

import com.example.weatherstation.factory.ObserverFactory;
import com.example.weatherstation.service.WeatherStationBuilder;
import com.example.weatherstation.strategy.BatchUpdateStrategy;
import com.example.weatherstation.strategy.RealTimeUpdateStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherstationApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherstationApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return args -> {
            var phone = ObserverFactory.create("mobile", "iPhone");
            var display = ObserverFactory.create("display", "Panel1");

            var station = new WeatherStationBuilder()
                    .addObserver(phone)
                    .addObserver(display)
                    .setStrategy(new RealTimeUpdateStrategy())
                    .build();

            station.updateWeather();
        };
    }
}