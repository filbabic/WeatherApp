package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;

public class Wind implements Serializable{
    private double speed;
    private double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Wind() {
        this.speed = 0;
        this.deg = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
