package com.reactivespring.funtional_design_patterns.iterator_design_pattern;

import java.util.function.Consumer;

public class MyArrayList {
    Object[] elements;

    public MyArrayList(Object[] elements) {
        this.elements = elements;
    }

    public void forEach(Consumer<Object> action) {
        for(int i=0; i<5; i++) {
            action.accept(elements[i]);
        }
    }
}
