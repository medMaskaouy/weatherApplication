package com.example.melmaska.weatherapplication.app.addWeather;

import com.example.melmaska.weatherapplication.BasePresenter;
import com.example.melmaska.weatherapplication.BaseView;

public interface AddWeatherContrator {

    interface Presenter extends BasePresenter{

         void addNewCity(String city);
    }

    interface  View extends BaseView<Presenter>{
        void showErrorMessage(int  res);
        void dismissDialog();
    }
}
