package com.reactivespring.combining_observables;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class MergingAndConcatenating {

    public static void main(String[] args) throws InterruptedException {
        @NonNull Observable<String> src1 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "src 1: " + e);
        @NonNull Observable<String> src2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "src 2: " + e);
//        Observable.merge(src1, src2)
//            .subscribe(e -> System.out.println("Received: " + e));
        Observable.concat(src1, src2)
                .subscribe(e -> System.out.println("Received: " + e));
        Thread.sleep(20000);
    }

}
