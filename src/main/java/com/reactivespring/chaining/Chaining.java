package com.reactivespring.chaining;

import java.util.function.Function;

public class Chaining {
    public static void main(String[] args) {
        Consumer<String> c1 = s -> System.out.println(s);
        Consumer<String> c2 = s -> System.out.println(s);
        Consumer<String> c3 = s -> {
            c1.accept(s);
            c2.accept(s);
        };
        c3.accept("Hello");

        Consumer<String> c4 = c1.thenAccept(c2);
        c4.accept("Chaining");

        Function<Integer, Integer> fun1 = s -> s+2;
        Function<Integer, Integer> fun2 = s -> s*2;

        Function<Integer, Integer> fun3 = fun1.andThen(fun2);
        System.out.println(fun3.apply(10));
    }
}
