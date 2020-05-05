package com.reactivespring;

public class Vehicle {
    private String name;
    private String model;
    private Float score;

    public Vehicle(String name, String model, Float score) {
        this.name = name;
        this.model = model;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
