package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Filip on 16/02/2016.
 */
public class Forecast implements Serializable {
    private ArrayList<WeatherResponse> list = new ArrayList<>();
    public Forecast(ArrayList<WeatherResponse> list) {
        this.list = list;
    }

    public Forecast() {
        this.list.add(new WeatherResponse());
    }

    public ArrayList<WeatherResponse> getList() {
        return list;
    }

    public void setList(ArrayList<WeatherResponse> list) {
        this.list = list;
    }

    public WeatherResponse getWeatherResponseObject(int position) {
        return list.get(position);
    }
}
