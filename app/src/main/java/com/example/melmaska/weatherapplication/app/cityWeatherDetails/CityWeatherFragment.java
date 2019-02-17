package com.example.melmaska.weatherapplication.app.cityWeatherDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.melmaska.weatherapplication.R;
import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.util.Utils;

public class CityWeatherFragment extends Fragment implements CityWeatherContractor.View {

    CityWeatherContractor.Presenter mPresenter;
    private CardViewItem cardViewItem;


    public static String TAG = "CityWeatherFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View cityWeatherView = inflater.inflate (R.layout.fragment_detail_weather, container, false);
        ImageView icon = cityWeatherView.findViewById(R.id.iv_detail_weather_icon);
        TextView description = cityWeatherView.findViewById(R.id.tv_detail_weatherDescription);
        TextView degree = cityWeatherView.findViewById(R.id.tv_detail_weather_degree);
        TextView cityName = cityWeatherView.findViewById(R.id.tv_detail_cityName);
        TextView humidity = cityWeatherView.findViewById(R.id.tv_detail_humidity);
        TextView pressure = cityWeatherView.findViewById(R.id.tv_detail_pressure);
        TextView wind = cityWeatherView.findViewById(R.id.tv_detail_windSpeed);
        //Toolbar toolbar = cityWeatherView.findViewById(R.id.toolbar);

        cityName.setText(cardViewItem.getCityName());
        description.setText(cardViewItem.getDescription());
        degree.setText(""+cardViewItem.getDegree());
        humidity.setText(cardViewItem.getHumidity()+"%");
        pressure.setText(""+cardViewItem.getPressure()+"mb");
        wind.setText(cardViewItem.getWind()+"m/s");
        icon.setImageDrawable(Utils.getDrawable(Utils.getWeatherIcon(cardViewItem.getIcon()), getActivity().getResources(), getActivity().getPackageName()));

        new CityWeatherPresenter(this);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return  cityWeatherView;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPresenter(CityWeatherContractor.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setCardViewItem(CardViewItem cardViewItem) {
        this.cardViewItem = cardViewItem;

    }
}
