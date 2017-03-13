package cs.dal.weatherapp.weather;

import cs.dal.weatherapp.weather.WeatherForecast;

/**
 * Created by duncanpulsifer on 2017-03-09.
 * A class used to store current location selection name, URL, and WeatherForecast object.
 */

public class GetWeather {

    private static String locationXML = null;
    private static String locationName = null;
    private static WeatherForecast weatherForecast = null;

    public GetWeather() { }

    public static void setLocationXML(String newLocation) {
        locationXML = newLocation;
    }

    public static String getLocationXML() { return locationXML; };

    public static void setLocationName(String locationName) {
        GetWeather.locationName = locationName;
    }

    public static String getLocationName() {
        return locationName;
    }

    public static void setWeatherForecast(WeatherForecast newWeatherForecast) {
        weatherForecast = newWeatherForecast;
    }

    public static WeatherForecast getWeatherForecast() { return weatherForecast; }

}
