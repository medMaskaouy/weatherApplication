package com.example.melmaska.weatherapplication;

public interface BaseView<T extends BasePresenter> {

     void showProgress();
     void hideProgress();
     void setPresenter(T presenter);
}