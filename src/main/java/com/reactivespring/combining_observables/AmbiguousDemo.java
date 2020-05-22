package com.reactivespring.combining_observables;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AmbiguousDemo {

    public static void main(String[] args) throws InterruptedException {
        @NonNull Observable<String> src1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(20)
                .map(e -> "src 1: " + e);

        @NonNull Observable<String> src2 = Observable.interval(10, TimeUnit.MILLISECONDS)
                .take(20)
                .map(e -> "src 2: " + e);

        Observable.amb(Arrays.asList(src1, src2))
                .subscribe(System.out::println);

        Thread.sleep(11000);

    }

}
