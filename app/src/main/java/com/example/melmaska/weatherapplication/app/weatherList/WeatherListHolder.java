package com.example.melmaska.weatherapplication.app.weatherList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.melmaska.weatherapplication.R;

public class WeatherListHolder  extends RecyclerView.ViewHolder{
    public ImageView icon;
    public TextView description;
    public TextView degree;
    public TextView cityName;
    public  ImageView trash;

    public WeatherListHolder(View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.iv_weather_icon);
        description = itemView.findViewById(R.id.tv_weatherDescription);
        degree = itemView.findViewById(R.id.tv_weather_degree);
        cityName =  itemView.findViewById(R.id.tv_cityName);
        trash = itemView.findViewById(R.id.iv_trash);
    }


}
