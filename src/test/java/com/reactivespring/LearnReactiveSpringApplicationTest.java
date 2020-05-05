package com.reactivespring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LearnReactiveSpringApplicationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void main() {
        assertEquals(true, true);

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("firstName1", "lastName1", 0.8f));
//        persons.add(new Person("firstName2", "lastName2", 0.79f));
        persons.add(new Person("firstName3", "lastName3", 0.9f));
        persons.add(new Person("firstName4", "lastName4", 0.95f));

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("vehicle1", "model1", 0.86f));
        vehicles.add(new Vehicle("vehicle2", "model2", 0.82f));
//        vehicles.add(new Vehicle("vehicle3", "model3", 0.78f));
        vehicles.add(new Vehicle("vehicle4", "model4", 0.9f));

        List<Person> personList = persons.stream().filter(person -> person.getScore() < 0.8f)
                .collect(Collectors.toList());

        if(personList.isEmpty()) {
            List<Vehicle> vehicleList = vehicles.stream().filter(vehicle -> vehicle.getScore() < 0.8f)
                    .collect(Collectors.toList());

            if(vehicleList.isEmpty()) {
                System.out.println("Verified!!!");
            }

        } else {
            System.out.println("Not verified!!!");
        }

    }
}