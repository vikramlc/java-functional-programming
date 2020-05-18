package com.reactivespring.streams.numericstreams.flatmap;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {
        Path path = Paths.get("C:/Users/1994/Documents/Notes/JavascriptNotes.txt");
        try (Stream<String> lines = Files.lines(path, Charset.forName("Cp1252"))) {

            lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
