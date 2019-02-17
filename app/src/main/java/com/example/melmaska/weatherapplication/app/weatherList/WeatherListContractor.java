package com.example.melmaska.weatherapplication.app.weatherList;


import android.support.v7.widget.SearchView;

import com.example.melmaska.weatherapplication.BasePresenter;
import com.example.melmaska.weatherapplication.BaseView;
import com.example.melmaska.weatherapplication.data.CardViewItem;

import java.util.List;


public interface WeatherListContractor {

     interface Presenter extends BasePresenter{

         void loadDefaultCities();
         void openDialogFragment();
         void filterCitiesByName();
         void openDetailFragment();
         void detailView(int position);
         void deleteView(int position);
         void subscribeToEventBus();
         void refreshData(List<CardViewItem> citiesList);

    }

     interface  View extends BaseView<Presenter>{

         void displayCities(List<CardViewItem> listCities);
         void addNewCity(CardViewItem city);
         void openDialogFragment();
         void openDetailFragment(int position);
         SearchView getSearchView();
         List<CardViewItem> getCardViewItems();
         void clearAdapterData();
         void setListItem(List<CardViewItem> list);

          interface WeatherOnClickListener {
             void onTrashClicked(int id);
             void onItemsClicked(int id);
         }
    }


}
