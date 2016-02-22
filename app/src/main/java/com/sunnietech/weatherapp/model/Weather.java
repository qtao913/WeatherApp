package com.sunnietech.weatherapp.model;

/**
 * Created by qlzh727 on 2/21/16.
 */
public class Weather {
    int temperature;
    String timestamp;
    String location;

    public Weather(int temperature, String timestamp, String location) {
        this.temperature = temperature;
        this.timestamp = timestamp;
        this.location = location;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
