package com.reactivespring.genericFunctionalInterfaces;

@FunctionalInterface
public interface FunctionalGenerics<T, R> {
    R execute(T t);
}
