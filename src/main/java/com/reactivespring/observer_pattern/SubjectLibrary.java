package com.reactivespring.observer_pattern;

public interface SubjectLibrary {
    void subscriberObserver(Observer o);
    void unsubscribeObserver(Observer o);
    void notifyObserver();
}
