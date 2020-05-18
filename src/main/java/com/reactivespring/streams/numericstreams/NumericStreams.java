package com.reactivespring.streams.numericstreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("The Alchemist", "Paul Coelho", "Adventure", 13.42332),
                new Book("The Notebook", "Nicholas Sparks", "Romantic", 9.75945),
                new Book("Harry Potter", "J.K. Rowling", "Sci-Fi", 7.34432),
                new Book("To kill a mocking bird", "Not sure", "Thriller", 6.34323)
        );

        double sum = books
                .stream()
                .mapToDouble(Book::getRating)
                .sum();
        System.out.println(sum);

        double average = books
                .stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0);
        System.out.println(average);

        double leastRating = books
                .stream()
                .mapToDouble(Book::getRating)
                .sorted()
                .findFirst()
                .orElse(0);
        System.out.println(leastRating);

        System.out.println("==================");

        books
                .stream()
                .peek(value -> System.out.println(value.getAuthor() + ": "))
                .mapToDouble(Book::getRating)
                .forEach(System.out::println);

        System.out.println("==================");

        int reduceSum = IntStream.range(0, 4)
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduceSum);

        double average1 = IntStream.of(10, 20, 30, 40).summaryStatistics().getAverage();
        System.out.println(average1);

        Stream.iterate(0, a -> a+1)
                .limit(10)
                .forEach(System.out::println);
    }
}
