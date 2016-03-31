package com.example.filip.weatherappmvpfinal.pojo;

import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public class Forecast {
    private ArrayList<WeatherResponse> list = new ArrayList<>();

    public Forecast(ArrayList<WeatherResponse> list) {
        this.list = list;
    }

    public ArrayList<WeatherResponse> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherResponse> list) {
        this.list = list;
    }
}
