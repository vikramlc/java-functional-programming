package com.reactivespring.observer_pattern;

public class EndUser implements Observer {
    private String name;

    public EndUser(String name, SubjectLibrary subject) {
        this.name = name;
        subject.subscriberObserver(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(String avail) {
        System.out.println("Hello " + this.name + ". We wanted to let you know that the book you subbscribed is " +
                avail);
    }
}
