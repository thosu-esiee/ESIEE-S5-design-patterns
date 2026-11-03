package fr.uge.poo.weatherservice.question3;

import com.evilcorp.weatherservice.WeatherService;
import com.evilcorp.weatherservice.WeatherServiceTSFail;

import java.util.Optional;

public class WeatherServices {

    private static final Object lock = new Object();
    private static WeatherService instance = null;

    public static Optional<WeatherService> getInstance() {
        synchronized (lock) {
            if (instance == null) {
                System.out.println("Connecting to WeatherServiceTSFail");
                try {
                    instance = new WeatherServiceTSFail();
                } catch (IllegalStateException e) {
                    System.err.println("Failed to connect to WeatherServiceTSFail");
                    return Optional.empty();
                }
            }
            return Optional.of(instance);
        }
    }

    private WeatherServices() {}
}
