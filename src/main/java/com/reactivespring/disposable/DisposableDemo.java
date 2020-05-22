package com.reactivespring.disposable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DisposableDemo {
    private static final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        @NonNull Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        @NonNull Disposable d1 = interval.subscribe(System.out::println);
        @NonNull Disposable d2 = interval.subscribe(System.out::println);

        Thread.sleep(5000);

        compositeDisposable.addAll(d1, d2);
        compositeDisposable.dispose();

        Thread.sleep(5000);
    }
}
