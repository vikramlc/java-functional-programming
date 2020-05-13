package com.reactivespring.genericFunctionalInterfaces;

public class FunctionalGenericDemo {

    public static void main(String[] args) {
        FunctionalGenerics<String, String> fun = s -> s.substring(2, 6);
        System.out.println(fun.execute("BasicJava"));

        FunctionalGenerics<String, Integer> fun1 = s -> s.length();
        System.out.println(fun1.execute("Be Strong!"));
    }

}
