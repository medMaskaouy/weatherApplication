package com.example.melmaska.weatherapplication.app.main;

import com.example.melmaska.weatherapplication.BasePresenter;
import com.example.melmaska.weatherapplication.BaseView;

public interface MainContractor {

    interface Presenter extends BasePresenter{
        void initWeather();
    }

    interface  View extends BaseView<Presenter>{
        void openWeatherListFragment();
    }
}
