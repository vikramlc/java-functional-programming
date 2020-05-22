package com.reactivespring.observable_variants;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ObservableVariants {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Max", "Alan", "Kenny");
        source.first("Max").subscribe(System.out::println);

        // 1. Single: Used in case if the Observable has only one element to be subscribed to.
        Single.just("Dev").subscribe(System.out::println);

        // 2. Maybe: Used in case if the Observable is empty.
        Observable<Object> empty = Observable.empty();
        empty.firstElement()
                .subscribe(System.out::println, e -> e.printStackTrace(),
                        () -> System.out.println("Completed"));

        // 3. Completable: Used to run an action in case of onComplete.
        // Example: In case of running a function in case of process or thread run execution.
        Completable completable = Completable.complete();
        completable.subscribe(() -> System.out.println("Completable: completed"));

        Completable
                .fromRunnable(() -> System.out.println("Process execution is in progress!"))
                .subscribe(() -> System.out.println("Process execution is completed!"));

    }
}
