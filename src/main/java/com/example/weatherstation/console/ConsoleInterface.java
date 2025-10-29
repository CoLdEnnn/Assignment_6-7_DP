package com.example.weatherstation.console;

import com.example.weatherstation.factory.ObserverFactory;
import com.example.weatherstation.observer.WeatherObserver;
import com.example.weatherstation.service.WeatherStation;
import com.example.weatherstation.strategy.BatchUpdateStrategy;
import com.example.weatherstation.strategy.ManualUpdateStrategy;
import com.example.weatherstation.strategy.RealTimeUpdateStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInterface implements CommandLineRunner {
    private final WeatherStation station;

    public ConsoleInterface(WeatherStation station) {
        this.station = station;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n🌤 Welcome to Weather Station Console 🌤");

        while (true) {
            System.out.print("1. Add Observer \n2. Remove Observer \n3. List Observers \n4. Change Strategy \n5. Update Weather \n0. Exit \nEnter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.print("Enter observer type (mobile/display): ");
                    String type = scanner.nextLine().trim();
                    System.out.print("Enter observer name: ");
                    String name = scanner.nextLine().trim();
                    if (type.isEmpty() || name.isEmpty()) {
                        System.out.println("Type and name are required.");
                        break;
                    }

                    WeatherObserver observer = ObserverFactory.create(type, name);
                    String addResult = station.addObserver(observer);
                    System.out.println(addResult);
                }
                case "2" -> {
                    System.out.print("Enter observer name to remove: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name is required.");
                        break;
                    }
                    String removeResult = station.removeObserver(name);
                    System.out.println(removeResult);
                }
                case "3" -> {
                    station.listObserverNames().forEach(n -> System.out.println(" - " + n));
                }
                case "4" -> {
                    System.out.print("Enter strategy (realtime/batch/manual): ");
                    String type = scanner.nextLine();
                    switch (type.toLowerCase()) {
                        case "realtime" -> station.setStrategy(new RealTimeUpdateStrategy());
                        case "batch" -> station.setStrategy(new BatchUpdateStrategy());
                        case "manual" -> station.setStrategy(new ManualUpdateStrategy());
                        default -> System.out.println("Unknown strategy type!");
                    }
                }
                case "5" -> station.updateWeather();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
