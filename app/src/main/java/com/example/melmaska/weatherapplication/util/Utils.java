package com.example.melmaska.weatherapplication.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.data.netWork.WeatherResult;

import java.util.ArrayList;

public class Utils {

    public static String API_URL = "http://api.openweathermap.org/data/2.5/";
    public static String APP_ID = "d01e9bdac1236862d852da15aea58edf";
    public static String WEATHER_LANG = "en";
    public static int convertKelvinToCelsius(double kelvin) {
        return (int) (kelvin - 273.15);
    }

    public static ArrayList<String> defaultCities(){
        ArrayList<String> defaultCities = new ArrayList<String>();
        defaultCities.add("casablanca");
        defaultCities.add("marrakech");
        defaultCities.add("rabat");
        defaultCities.add("tanger");
        defaultCities.add("fes");

        return defaultCities;
    }

    public static CardViewItem apiResponseToCardViewObject(WeatherResult weatherResult) {
        String icon = "";
        String description = "";
        double degree = 0;
        int humidty = 0;
        float pressure = 0;
        float wind = 0;
        String  cityName = weatherResult.name;

        if (weatherResult.weather != null) {
            if (weatherResult.weather.size() > 0)
                icon = weatherResult.weather.get(0).icon;
            description = weatherResult.weather.get(0).description;
        }

        if (weatherResult.main != null) {
            degree = (weatherResult.main.temp == null) ? 0 : weatherResult.main.temp;
            humidty = (weatherResult.main.humidity == null) ? 0 : weatherResult.main.humidity;
            pressure = (weatherResult.main.pressure == null) ? 0 : weatherResult.main.pressure;
        }
        if (weatherResult.wind != null) {
            wind = (weatherResult.wind.speed == null) ? 0 : weatherResult.wind.speed;
        }
        return new CardViewItem( icon,description, Utils.convertKelvinToCelsius(degree) ,cityName,humidty,pressure,wind);
    }

    public static Drawable getDrawable(String name, Resources resources, String packageName) {
        int resourceId = resources.getIdentifier(name, "drawable", packageName);
        return resources.getDrawable(resourceId);
    }
    public static String getWeatherIcon(String str){
        String icon;
        switch (str) {
            //  clear sky
            case "01d":
                icon = "sw_01";
                break;
            case "01n":
                icon = "sw_02";
                break;
            // few clouds
            case "02d":
                icon = "sw_03";
                break;
            case "02n":
                icon = "sw_07";
                break;
            //  scattered clouds
            case "03d":
                icon = "sw_04";
                break;
            case "03n":
                icon = "sw_04";
                break;
            //   broken clouds
            case "04d":
                icon = "sw_06";
                break;
            case "04n":
                icon = "sw_06";
                break;
            //    shower rain
            case "09d":
                icon = "sw_23";
                break;
            case "09n":
                icon = "sw_23";
                break;
            //    rain
            case "10d":
                icon = "sw_12";
                break;
            case "10n":
                icon = "sw_32";
                break;
            //   thunderstorm
            case "11d":
                icon = "sw_17";
                break;
            case "11n":
                icon = "sw_37";
                break;
            //   snow
            case "12d":
                icon = "sw_15";
                break;
            case "12n":
                icon = "sw_35";
                break;
            //   mist
            case "13d":
                icon = "sw_10";
                break;
            case "13n":
                icon = "sw_30";
                break;
            default:
                icon = "error_icon";
        }
        return icon;
    }
}
