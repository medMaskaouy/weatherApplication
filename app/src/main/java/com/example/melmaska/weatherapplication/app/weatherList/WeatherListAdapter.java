package com.example.melmaska.weatherapplication.app.weatherList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.melmaska.weatherapplication.R;
import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class WeatherListAdapter  extends RecyclerView.Adapter<WeatherListHolder>  {

    private Context mContext;
    private List<CardViewItem> citiesList;
    WeatherListContractor.View.WeatherOnClickListener weatherOnClickListener;

    public WeatherListAdapter(Context context, WeatherListContractor.View.WeatherOnClickListener weatherOnClickListener) {
        mContext = context;
        this.weatherOnClickListener = weatherOnClickListener;
        citiesList = new ArrayList<>();
    }

    @Override
    public WeatherListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (mContext).inflate (R.layout.cardview_city, parent, false);
        final WeatherListHolder collecteHolder = new WeatherListHolder (view);
        return collecteHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListHolder holder, int position) {
        CardViewItem item = citiesList.get (position);
        holder.degree.setText(""+item.getDegree());
        holder.cityName.setText(item.getCityName());
        holder.description.setText(item.getDescription());
        holder.icon.setImageDrawable(Utils.getDrawable(Utils.getWeatherIcon(item.getIcon()), mContext.getResources(), mContext.getPackageName()));

        // Setting onClickListners
        holder.trash.setOnClickListener(view -> weatherOnClickListener.onTrashClicked(holder.getAdapterPosition()));
        holder.degree.setOnClickListener(view -> weatherOnClickListener.onItemsClicked(holder.getAdapterPosition()));
        holder.cityName.setOnClickListener(view -> weatherOnClickListener.onItemsClicked(holder.getAdapterPosition()));
        holder.description.setOnClickListener(view -> weatherOnClickListener.onItemsClicked(holder.getAdapterPosition()));
        holder.icon.setOnClickListener(view -> weatherOnClickListener.onItemsClicked(holder.getAdapterPosition()));

    }


    @Override
    public int getItemCount() {
        if(citiesList ==  null) return 0;
        return citiesList.size ();
    }

    public void updateData(List<CardViewItem> list) {
        this.citiesList = list;
        notifyDataSetChanged ();
    }

    public void addNewCity(CardViewItem city) {
        this.citiesList.add(city);
        notifyDataSetChanged ();
    }

    public List<CardViewItem> getCitiesList() {
        return citiesList;
    }

    public void resetData() {
         citiesList.clear();
         notifyDataSetChanged ();
    }
}