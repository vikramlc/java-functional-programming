package com.reactivespring.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {
        List<String> demoList = Arrays.asList("Time", "", "tide", "", "waits", "for", "", "none");
        Predicate<String> nonEmptyList = s -> !s.isEmpty();
        System.out.println(filterList(demoList, nonEmptyList));

        Predicate<String> isGreaterThanFour = s -> s.length() > 4;
        System.out.println(filterList(demoList, isGreaterThanFour));

        List<Integer> coronaImmunityReports = Arrays.asList(81, 80, 40, 89, 79, 90);
        Predicate<Integer> isCoronaPositive = s -> s > 80;
        System.out.println(filterList(coronaImmunityReports, isCoronaPositive));
    }

    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for(T value: list) {
            if(predicate.test(value)) {
                newList.add(value);
            }
        }

        return newList;
    }
}
