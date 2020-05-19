package com.reactivespring.observer_pattern;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Book book = new Book("Harry Potter", "Fiction",
                "J.K.Rowling", 13.99, "SOLD OUT");
        EndUser user1 = new EndUser("Max", book);
        EndUser user2 = new EndUser("Jason", book);
        System.out.println(book.getInStock());
        book.setInStock("IN STOCK");
    }
}
