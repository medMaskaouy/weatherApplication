package com.example.melmaska.weatherapplication.app.addWeather;

import com.example.melmaska.weatherapplication.api.ApiClient;
import com.example.melmaska.weatherapplication.api.ApiWeather;
import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.util.Utils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddWeatherInteractor {

    public static Observable<CardViewItem> getCityWeatherObservable(String city) {
        ApiWeather apiWeather = ApiClient.providesRetrofit ().create (ApiWeather.class);
        return apiWeather.loadWeatherByCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map( result -> {
                    if(result.cod == 200){
                        return Utils.apiResponseToCardViewObject(result);
                    }else{
                        return null;
                    }
                });
    }

 }
