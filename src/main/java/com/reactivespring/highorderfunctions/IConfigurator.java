package com.reactivespring.highorderfunctions;

public interface IConfigurator<T, R> {
    R configure(T t);
}
