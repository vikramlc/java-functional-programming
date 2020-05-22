package com.reactivespring.combining_observables;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;

public class FlatmapAndConcatMap {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Print", "these", "lines");

        System.out.println("FlatMap Demo");
        Observable.fromIterable(list)
                .flatMap(e -> Observable.fromArray(e.split("")))
                .subscribe(System.out::println);

        System.out.println("ConcatMap Demo");
        Observable.fromIterable(list)
                .concatMap(e -> Observable.fromArray(e.split("")))
                .subscribe(System.out::println);

    }
}
