package com.example.melmaska.weatherapplication.app.weatherList;

import android.support.v7.widget.SearchView;
import com.example.melmaska.weatherapplication.api.ApiClient;
import com.example.melmaska.weatherapplication.api.ApiWeather;
import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class WeatherListInteractor  {



    public static Observable<CardViewItem> getCityObservable(String city) {
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


    public static Observable<String> fromSearchView(SearchView searchView) {

        final PublishSubject<String> publishSubject  = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                publishSubject.onNext(s);
                 return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                publishSubject.onNext(text);
                 return true;
            }
        });

        return publishSubject;
    }

    public static Observable<List<CardViewItem>>  applyFilterName(String name,List<CardViewItem> mList){
        return Observable.create(emitter -> {
            if(!emitter.isDisposed()){
                List<CardViewItem> tmp = new ArrayList<CardViewItem>();
                for(CardViewItem item :mList){
                    if( item.getCityName().toLowerCase().contains(name.toLowerCase()))
                        tmp.add(item);
                }
                emitter.onNext(tmp);
                emitter.onComplete();
            }

        });
    }

    public static Observable<String>  refreshDataObservable(List<CardViewItem> mList){
        return Observable.create(emitter -> {
            if(!emitter.isDisposed()){
                for(CardViewItem item :mList){
                    emitter.onNext(item.getCityName());
                }
                emitter.onComplete();
            }

        });
    }
}
