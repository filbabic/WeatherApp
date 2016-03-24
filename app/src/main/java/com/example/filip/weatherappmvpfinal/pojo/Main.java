package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;

public class Main implements Serializable{
    private double temp;
    private double temp_min;
    private double temp_max;
    private int humidity;
    private double pressure;

    public Main(double temp, double temp_min, double temp_max, int humidity, double pressure) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Main() {
        this.temp = 0;
        this.temp_min = 0;
        this.temp_max = 0;
        this.pressure = 0;
        this.humidity = 0;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
