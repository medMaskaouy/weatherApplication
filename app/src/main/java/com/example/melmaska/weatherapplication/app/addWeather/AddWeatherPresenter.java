package com.example.melmaska.weatherapplication.app.addWeather;

import com.example.melmaska.weatherapplication.R;
import com.example.melmaska.weatherapplication.data.CardViewItem;
import com.example.melmaska.weatherapplication.data.RxBusEvent;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class AddWeatherPresenter implements  AddWeatherContrator.Presenter {
    AddWeatherContrator.View mView;
    CompositeDisposable compositeDisposable;

    public AddWeatherPresenter(AddWeatherContrator.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void addNewCity(String city) {
        mView.showProgress();
        Disposable d = AddWeatherInteractor.getCityWeatherObservable(city)
                .subscribeWith(new DisposableObserver<CardViewItem>() {
                    @Override
                    public void onNext(CardViewItem cardViewItem) {
                        if(cardViewItem != null)
                             RxBusEvent.instanceOf().send(cardViewItem);
                        mView.hideProgress();
                        mView.dismissDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorMessage(R.string.cityNotFound);
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(d);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }

}
