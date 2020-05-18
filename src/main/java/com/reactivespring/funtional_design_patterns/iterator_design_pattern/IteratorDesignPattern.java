package com.reactivespring.funtional_design_patterns.iterator_design_pattern;

public class IteratorDesignPattern {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(new Object[] {1, 2, 3, 4, 5});
        list.forEach(el -> System.out.println(el));
    }
}
