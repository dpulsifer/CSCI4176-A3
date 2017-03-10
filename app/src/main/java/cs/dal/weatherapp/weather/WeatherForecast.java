package cs.dal.weatherapp.weather;

import java.util.ArrayList;

import cs.dal.weatherapp.weather.ForecastEntry;

/**
 * Created by duncanpulsifer on 2017-03-08.
 */

public class WeatherForecast {

    String location;
    String updateTime;
    ArrayList<ForecastEntry> forecast;

    public WeatherForecast() {
        forecast = new ArrayList<>();
    }

    public WeatherForecast(String location, String updateTime, ArrayList<ForecastEntry> forecast) {
        this.location = location;
        this.updateTime = updateTime;
        this.forecast = forecast;
    }

    public String getLocation() {
        return location;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public ArrayList<ForecastEntry> getForecast() {
        return forecast;
    }

    public ArrayList<String> getShortForecast() {
        ArrayList<String> shortForecast = new ArrayList<String>();

        for (int i = 0; i < forecast.size(); i++) {
            shortForecast.add(forecast.get(i).getTitle());
        }
        return shortForecast;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setForecast(ArrayList<ForecastEntry> forecast) {
        this.forecast = forecast;
    }

    public void addForecastEntry(ForecastEntry entry) {
        forecast.add(entry);
    }

}
