package fr.uge.poo.weatherservice.question2;

import com.evilcorp.weatherservice.WeatherService;

public class WeatherServices {

    private static final WeatherService instance = new WeatherServiceProxy();

    public static WeatherService getInstance() {
        return instance;
    }

    private WeatherServices() {}
}
