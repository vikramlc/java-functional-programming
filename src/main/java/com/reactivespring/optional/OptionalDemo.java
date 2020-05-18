package com.reactivespring.optional;

// https://www.baeldung.com/java-optional

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalDemo {

    public static void main(String[] args) {
        // Empty optionals
        Optional<String> empty = Optional.empty();
        System.out.println("empty optional value: " + (empty.isPresent()? "present": "not present"));

        // Of optionals: the value should not be null as it will throw a null pointer exception
        String name = "optional course";
        Optional<String> stringOpt = Optional.of(name);
        stringOpt.ifPresent(s -> System.out.println("stringOpt: " + s));
        System.out.println("of optional value: " + stringOpt.get());

        /*String value = null;
        Optional.of(value); // throws NullPointerException*/

        // ofNullable optionals: To use a value which can be nullable
        String value = null;
        Optional<String> nullableOpt = Optional.ofNullable(value);
        System.out.println("nullableOpt: " + nullableOpt.isPresent());

        // orElse optionals
        String nullValue = null;
        String orElseValue = Optional.ofNullable(nullValue).orElse("default");
        System.out.println("orElseValue: " + orElseValue);

        // orElseGet optionals
        String nullName = null;
        String orElseGetValue = Optional.ofNullable(nullName).orElseGet(() -> {
            String def = "defaultGetValue";
            return def;
        });
        System.out.println("orElseGetValue: " + orElseGetValue);

        System.out.println("============================");

        // Difference between orElse() and orElseGet()
        // orElseGet: getDefault() method is not called in case the value is present
        // orElse: getDefault() method is called even if the value is present
        String text = "text present";
        System.out.println("Using orElseGet");
        String defaultText = Optional.ofNullable(text).orElseGet(OptionalDemo::getDefaultText);
        System.out.println("defaultText: " + defaultText);

        System.out.println("Using orElse");
        defaultText = Optional.ofNullable(text).orElse(getDefaultText());
        System.out.println("defaultText: " + defaultText);

        System.out.println("============================");

        // orElseThrow optionals
        String param = "dummy";
        String paramValue = Optional.ofNullable(param).orElseThrow(IllegalStateException::new);

        // get optionals: Throws NoSuchElementException in case of null value
        // Never use get() on a null value
        /*String param1 = null;
        Optional<String> getOpt = Optional.ofNullable(param1);
        String getValue = getOpt.get();*/

        System.out.println("============================");

        // filter optionals
        basicOptionalFilter();

        System.out.println("============================");

        advancedOptionalFilter();

        System.out.println("============================");

        // map optionals
        mapOptional();

        System.out.println("============================");

        // flatmap optionals
        flapMapOptional();

        System.out.println("============================");

        // Chaining Optionals
        // Avoid using optionals in functional parameters
        givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned();
        givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated();

        System.out.println("============================");

        // Misuage of optionals: NOT RECOMMENDED
        List<Person> people = Arrays.asList(
                new Person("Demo", 23, "pass"),
                new Person("Peter", 28, "pass"),
                new Person("Max", 30, "pass")
        );
        System.out.println(search(people, "Peter", null).size());
    }

    public static List<Person> search(List<Person> people, String name, Optional<Integer> age) {
        // Null checks for people and name
        return people.stream()
                .filter(p -> p.getName().equals(name))
                .filter(p -> p.getAge().get() >= age.orElse(0))
                .collect(Collectors.toList());
    }

    private static Optional<String> getEmpty() {
        return Optional.empty();
    }

    private static Optional<String> getHello() {
        return Optional.of("hello");
    }

    private static Optional<String> getBye() {
        return Optional.of("bye");
    }

    private Optional<String> createOptional(String input) {
        if (input == null || "".equals(input) || "empty".equals(input)) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    public static void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturned() {
        Optional<String> found = Stream.of(getEmpty(), getHello(), getBye())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        System.out.println("Found: " + found.get());
    }

    public static void givenThreeOptionals_whenChaining_thenFirstNonEmptyIsReturnedAndRestNotEvaluated() {
        Optional<String> found = Stream.<Supplier<Optional<String>>>of(OptionalDemo::getEmpty, OptionalDemo::getHello, OptionalDemo::getBye)
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        System.out.println("Found: " + found.get());
    }

    private static void flapMapOptional() {
        Person person = new Person("Max", 30, "password");
        Optional<Person> personOptional = Optional.of(person);

        String personName = personOptional
                .flatMap(Person::getName)
                .orElse("");
        System.out.println("personName: " + personName);

    }

    private static void mapOptional() {
        String password = "   password  ";
        Optional<String> passOpt = Optional.of(password);
        boolean correctPassword = passOpt.filter(pass -> pass.equalsIgnoreCase("password")).isPresent();
        System.out.println("correctPassword :: before -> " + correctPassword);
        correctPassword = passOpt
                .map(String::trim)
                .filter(pass -> pass.equalsIgnoreCase("password"))
                .isPresent();
        System.out.println("correctPassword :: after -> " + correctPassword);
    }

    private static void advancedOptionalFilter() {
        System.out.println(OptionalDemo.isPriceInRange(new Modem(10.9)));
        System.out.println(OptionalDemo.isPriceInRange(new Modem(9.9)));
        System.out.println(OptionalDemo.isPriceInRange(new Modem(null)));
        System.out.println(OptionalDemo.isPriceInRange(null));
    }

    private static void basicOptionalFilter() {
        Predicate<Integer> is2016YearCheckPredicate = year -> year == 2016;
        Optional<Integer> filterStringOpt = Optional.of(2017);
        boolean is2016Year = filterStringOpt.filter(year -> is2016YearCheckPredicate.test(2016)).isPresent();
        System.out.println("is2016Year: " + is2016Year);
        boolean is2018Year = filterStringOpt.filter(year -> is2016YearCheckPredicate.test(2018)).isPresent();
        System.out.println("is2018Year: " + is2018Year);

    }

    private static String getDefaultText() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

    public static boolean isPriceInRange(Modem modem) {
        return Optional.ofNullable(modem)
                .map(Modem::getPrice)
                .filter(p -> p>=10)
                .filter(p -> p<=15)
                .isPresent();
    }

    public static class Modem {
        private Double price;

        public Modem(Double price) {
            this.price = price;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
