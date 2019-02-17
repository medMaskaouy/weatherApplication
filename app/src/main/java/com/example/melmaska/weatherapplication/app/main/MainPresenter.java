package com.example.melmaska.weatherapplication.app.main;

public class MainPresenter implements MainContractor.Presenter {
    private MainContractor.View mainActivity;

    public MainPresenter(MainContractor.View mainActivity) {
        this.mainActivity = mainActivity;
        mainActivity.setPresenter(this);
    }

    @Override
    public void initWeather() {
        mainActivity.openWeatherListFragment();
    }

    @Override
    public void onDestroy() {

    }
}
