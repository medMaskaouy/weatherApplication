package com.example.melmaska.weatherapplication.app.main;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.melmaska.weatherapplication.R;
import com.example.melmaska.weatherapplication.app.weatherList.WeatherListFragment;

public class MainActivity extends AppCompatActivity implements MainContractor.View{
    private MainContractor.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainPresenter(this).initWeather();
 }


    @Override
    public void openWeatherListFragment() {
         Fragment weatherFragment =  getSupportFragmentManager().findFragmentByTag(WeatherListFragment.TAG);
        if (weatherFragment == null) {
            weatherFragment = new WeatherListFragment();
        }
        FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();
        transaction.add (R.id.contentFrame, weatherFragment,WeatherListFragment.TAG);
        transaction.commit ();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPresenter(MainContractor.Presenter presenter) {
        mPresenter = presenter;
    }
}
