package com.reactivespring.combining_observables;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ZipAndCombineLatest {

    public static void main(String[] args) throws InterruptedException {
        @NonNull Observable<Long> src1 = Observable.interval(100, TimeUnit.MILLISECONDS);
        @NonNull Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS);

        /*Observable.zip(src1, src2, (e1, e2) -> "src1: " + e1 + ", src2: " + e2)
                .subscribe(System.out::println);*/

        Observable.combineLatest(src1, src2, (e1, e2) -> "src1: " + e1 + ", src2: " + e2)
                .subscribe(System.out::println);

        Thread.sleep(3000);
    }

}
