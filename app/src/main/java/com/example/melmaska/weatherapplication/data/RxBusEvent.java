package com.example.melmaska.weatherapplication.data;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBusEvent {
    public static RxBusEvent instance;

    public static RxBusEvent instanceOf() {
        if (instance == null) {
            instance = new RxBusEvent();
        }
        return instance;
    }
    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object object) {
        bus.onNext(object);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
