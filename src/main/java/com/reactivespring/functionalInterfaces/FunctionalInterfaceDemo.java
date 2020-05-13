package com.reactivespring.functionalInterfaces;

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {

        /*MyFunInterface funInterface = () -> System.out.println("Hello");
        funInterface.myMethod();*/

        onTheFly(() -> System.out.println("Hello"));

    }

    public static void onTheFly(MyFunInterface funInterface) {
        funInterface.myMethod();
    }

}
