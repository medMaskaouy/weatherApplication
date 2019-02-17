package com.example.melmaska.weatherapplication.app.weatherList;


import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.data.RxBusEvent;
import com.example.melmaska.weatherapplication.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherListPresenter implements  WeatherListContractor.Presenter {

    private WeatherListContractor.View  mView;
    private CompositeDisposable compositeDisposable;


    public WeatherListPresenter(WeatherListContractor.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
        subscribeToEventBus();
    }


    @Override
    public void loadDefaultCities() {
        mView.showProgress();
        ArrayList<String> defaultCities = Utils.defaultCities();
        Disposable d = Observable.fromIterable(defaultCities)
                .flatMap(WeatherListInteractor::getCityObservable)
                .subscribeWith(new DisposableObserver<CardViewItem>() {
                    @Override
                    public void onNext(CardViewItem cardViewItem) {
                        if(cardViewItem != null)
                            mView.addNewCity(cardViewItem);
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        compositeDisposable.add(d);

    }

    @Override
    public void openDialogFragment() {
        mView.openDialogFragment();
    }

    @Override
    public void filterCitiesByName() {
       Disposable d = WeatherListInteractor.fromSearchView(mView.getSearchView())
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(text -> {
                    if (text.isEmpty()) {
                        mView.displayCities(mView.getCardViewItems());
                        return false;
                    } else {
                        return true;
                    }
                })
                .distinctUntilChanged()
                .flatMap(s -> WeatherListInteractor.applyFilterName(s, mView.getCardViewItems()))
                .subscribe(result -> mView.displayCities(result));

             compositeDisposable.add(d);
    }

    @Override
    public void openDetailFragment() {

    }

    @Override
    public void detailView(int position) {
        mView.openDetailFragment(position);
    }

    @Override
    public void deleteView(int position) {
        List<CardViewItem> mList = mView.getCardViewItems();
        mList.remove(position);
        mView.displayCities(mList);
    }

    @Override
    public void subscribeToEventBus() {
        compositeDisposable.add(RxBusEvent
                .instanceOf()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof CardViewItem) {
                        mView.addNewCity((CardViewItem)object);
                    }
                }));
    }

    @Override
    public void refreshData(List<CardViewItem> citiesList) {
        mView.showProgress();
         Disposable d = WeatherListInteractor.refreshDataObservable(citiesList)
                .flatMap(WeatherListInteractor::getCityObservable)
                .toList()
                .subscribe(cardViewItems -> {
                    mView.clearAdapterData();
                    mView.displayCities(cardViewItems);
                    mView.setListItem(cardViewItems);
                    mView.hideProgress();
                });
         compositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
