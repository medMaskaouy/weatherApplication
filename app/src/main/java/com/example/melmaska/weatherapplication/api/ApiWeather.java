package com.example.melmaska.weatherapplication.api;

import com.example.melmaska.weatherapplication.data.netWork.WeatherResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiWeather {

    @GET("weather")
    Observable<WeatherResult> loadWeatherByCity(@Query("q") String city);

}
