package com.example.melmaska.weatherapplication.data;

public class CardViewItem {

    private String icon;
    private String description;
    private int degree;
    private String cityName;
    private  int humidity;
    private  float pressure;
    private  float wind;

    public CardViewItem(String icon, String description, int degree, String cityName, int humidity, float pressure, float wind) {
        this.icon = icon;
        this.description = description;
        this.degree = degree;
        this.cityName = cityName;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getWind() {
        return wind;
    }

    public String getIcon() {

        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
