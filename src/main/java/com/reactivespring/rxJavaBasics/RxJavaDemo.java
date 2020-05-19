package com.reactivespring.rxJavaBasics;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxJavaDemo {
    public static void main(String[] args) throws InterruptedException {

        // Different ways to create Observables

        // 1. Create
        Observable<String> source = Observable.create(e -> {
            e.onNext("Hello");
            e.onNext("RxJava");
        });

        source.subscribe(e -> System.out.println("Observer 1: " + e));
        source.subscribe(e -> System.out.println("Observer 2: " + e));

        System.out.println("=====================");

        // 2. Just
        Observable<Integer> just = Observable.just(1, 2, 3);
        just.subscribe(System.out::println);

        System.out.println("=====================");

        // 3. FromIterable
        List<Integer> list = Arrays.asList(2, 4, 6, 8);
        Observable<Integer> fromIterable = Observable.fromIterable(list);
        fromIterable.subscribe(System.out::println);

        System.out.println("=====================");

        // 4. Range
        Observable<Integer> range = Observable.range(5, 15);
        range.subscribe(System.out::println);

        System.out.println("=====================");

        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        interval.subscribe(System.out::println);
    }
}
