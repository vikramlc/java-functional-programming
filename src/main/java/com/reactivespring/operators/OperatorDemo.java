package com.reactivespring.operators;

import io.reactivex.rxjava3.core.Observable;

public class OperatorDemo {
    public static void main(String[] args) {
        Observable.just(12, 45, 10, 33, 99, 1, 0)
                .sorted()
                .filter(e -> e<40)
                .subscribe(System.out::println);
    }
}
