package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class WeatherResponse implements Serializable {
    private Weather[] weather = new Weather[1];
    private Main main;
    private Wind wind;
    private String dt_txt;

    public WeatherResponse(Weather[] weather, Main main, Wind wind, String dt_txt) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.dt_txt = dt_txt;
    }

    public WeatherResponse() {
        this.weather = new Weather[]{new Weather()};
        this.main = new Main();
        this.wind = new Wind();
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeatherObject() {
        return weather[0];
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
