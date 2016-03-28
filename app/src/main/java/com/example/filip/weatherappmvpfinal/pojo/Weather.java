package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;

public class Weather implements Serializable {
    private String main;
    private String description;

    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public Weather() {
        this.main = "N/A";
        this.description = "N/A";
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
