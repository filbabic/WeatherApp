package com.example.filip.weatherappmvpfinal.pojo;

import java.io.Serializable;

public class Weather implements Serializable{
    private String main;
    private String description;
    private int id;

    public Weather(String main, String description, int id) {
        this.main = main;
        this.description = description;
        this.id = id;
    }

    public Weather() {
        this.main = "N/A";
        this.description = "N/A";
        this.id = 0;
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

    public int getIcon() {
        return id;
    }

    public void setIcon(int icon) {
        this.id = icon;
    }
}
