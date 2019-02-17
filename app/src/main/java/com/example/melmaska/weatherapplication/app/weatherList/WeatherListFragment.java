package com.example.melmaska.weatherapplication.app.weatherList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.melmaska.weatherapplication.R;
import com.example.melmaska.weatherapplication.app.addWeather.AddWeatherFragment;
import com.example.melmaska.weatherapplication.app.cityWeatherDetails.CityWeatherFragment;
import com.example.melmaska.weatherapplication.data.CardViewItem;

import java.util.ArrayList;
import java.util.List;

public class WeatherListFragment extends Fragment implements  WeatherListContractor.View{

    private WeatherListContractor.Presenter mPresenter;
    private SearchView searchView = null;
    List<CardViewItem> mListOfCardViews;
    RecyclerView mWeatherCitiesRecyclerView;
    WeatherListAdapter  weatherListAdapter;
    FloatingActionButton fab_add_city;
    LinearLayout weatherContent;
    SwipeRefreshLayout pullToRefresh;
    ProgressBar mProgressBar;
    public static String TAG = "WeatherListFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View weatherView = inflater.inflate (R.layout.fragment_weather_list, container, false);

        new WeatherListPresenter(this);
        searchView=(SearchView)weatherView.findViewById(R.id.searchView);
        fab_add_city = (FloatingActionButton) weatherView.findViewById(R.id.fab_add_city);
        fab_add_city.setOnClickListener(view -> mPresenter.openDialogFragment());
        fab_add_city.setVisibility(View.VISIBLE);
        mProgressBar= weatherView.findViewById(R.id.pb_weather_list);

        weatherContent = (LinearLayout)weatherView.findViewById(R.id.lL_weather_content);
        weatherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openDetailFragment();
            }
        });

        setHasOptionsMenu(true);

        weatherListAdapter = new WeatherListAdapter (getActivity(), new WeatherOnClickListener() {
            @Override
            public void onTrashClicked(int id) {
              mPresenter.deleteView(id);
            }

            @Override
            public void onItemsClicked(int id) {
                mPresenter.detailView(id);
            }
        });

        mListOfCardViews = new ArrayList<> ();
        mWeatherCitiesRecyclerView = (RecyclerView) weatherView.findViewById (R.id.citiesRecyclerView);
        mWeatherCitiesRecyclerView.setHasFixedSize (true);
        mWeatherCitiesRecyclerView.setLayoutManager (new LinearLayoutManager(getActivity()));
        weatherListAdapter.updateData (mListOfCardViews);
        mWeatherCitiesRecyclerView.setAdapter (weatherListAdapter);

        mPresenter.loadDefaultCities();
        mPresenter.filterCitiesByName();

        pullToRefresh = weatherView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(() -> {
           mPresenter.refreshData( mListOfCardViews);
            pullToRefresh.setRefreshing(false);
        });


        return weatherView;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(WeatherListContractor.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void displayCities(List<CardViewItem> listCities) {
        weatherListAdapter.updateData(listCities);

    }



    @Override
    public void addNewCity(CardViewItem city) {
        weatherListAdapter.addNewCity(city);
    }

    @Override
    public void openDialogFragment() {
        Fragment addWeatherFragment = getActivity().getSupportFragmentManager().findFragmentByTag(AddWeatherFragment.TAG);
        if (addWeatherFragment == null) {
            addWeatherFragment = new AddWeatherFragment();
            addWeatherFragment.setTargetFragment(this, 0);
        }
        ((AddWeatherFragment)addWeatherFragment).show(getFragmentManager(), AddWeatherFragment.TAG);
    }

    @Override
    public void openDetailFragment(int position) {
        Fragment cityWeatherFragment = getActivity().getSupportFragmentManager().findFragmentByTag(CityWeatherFragment.TAG);
        if (cityWeatherFragment == null) {
            cityWeatherFragment = new CityWeatherFragment();
        }
        getActivity().getSupportFragmentManager ()
                .beginTransaction ()
                .add (R.id.contentFrame, cityWeatherFragment,CityWeatherFragment.TAG)
                .addToBackStack(null)
                .commit ();
        ((CityWeatherFragment)cityWeatherFragment).setCardViewItem(mListOfCardViews.get(position));

    }

    @Override
    public SearchView getSearchView() {
        return searchView;
    }

    @Override
    public List<CardViewItem> getCardViewItems() {
        return mListOfCardViews;
    }

    @Override
    public void clearAdapterData() {
        weatherListAdapter.resetData();
    }

    @Override
    public void setListItem(List<CardViewItem> list) {
        mListOfCardViews = list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
