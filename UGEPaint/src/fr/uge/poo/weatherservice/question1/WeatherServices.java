package fr.uge.poo.weatherservice.question1;

import com.evilcorp.weatherservice.WeatherService;
import com.evilcorp.weatherservice.WeatherServiceTS;

public class WeatherServices {

    private static final WeatherService instance = new WeatherServiceTS();

    public static WeatherService getInstance() {
        return instance;
    }

    private WeatherServices() {}
}
