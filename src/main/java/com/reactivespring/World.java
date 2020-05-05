package com.reactivespring;

import java.util.ArrayList;
import java.util.List;

public class World {
    private String name;
    private List<Person> personList = new ArrayList<>();

    public World(String name, List<Person> personList) {
        this.name = name;
        this.personList = personList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
