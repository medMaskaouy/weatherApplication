package com.example.melmaska.weatherapplication.app.cityWeatherDetails;

public class CityWeatherPresenter implements  CityWeatherContractor.Presenter {

    CityWeatherContractor.View mView;

    public CityWeatherPresenter(CityWeatherContractor.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void onDestroy() {
    }
}
