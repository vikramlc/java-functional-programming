package com.reactivespring.observer_pattern;

import java.util.ArrayList;
import java.util.List;

public class Book implements SubjectLibrary {
    private String name;
    private String type;
    private String author;
    private double price;
    private String inStock;
    private List<Observer> observerList = new ArrayList<>();

    public Book(String name, String type, String author, double price, String inStock) {
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
        notifyObserver();
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<Observer> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void subscriberObserver(Observer o) {
        this.observerList.add(o);
    }

    @Override
    public void unsubscribeObserver(Observer o) {
        this.observerList.remove(0);
    }

    @Override
    public void notifyObserver() {
        System.out.println("Book{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", inStock='" + inStock + '\'' +
                '}' + "is " + this.inStock + " . Please notify all the users.");
        for (Observer o: observerList) {
            o.update(this.inStock);
        }

    }
}
