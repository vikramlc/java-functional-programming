package com.reactivespring;

import io.reactivex.Flowable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
*/

@SpringBootApplication
public class LearnReactiveSpringApplication {

	public static void main(String[] args) {

		Flowable.just("Hello World!!").subscribe(System.out::println);

		/*
		try(Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\1994\\Documents\\Personal\\Docs"))) {
			*//*
			// To print all file names under a folder
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString())
					.collect(Collectors.toList());

			result.forEach(x -> System.out.println(x.substring(x.lastIndexOf("\\") + 1)));
			*//*

			*//*
			// To print all the directories under a folder
			List<String> result = walk.filter(Files::isDirectory)
					.map(x -> x.toString())
					.collect(Collectors.toList());

			result.forEach(x -> System.out.println(x.substring(x.lastIndexOf("\\") + 1)));
			*//*

			*//*
			// To print all files which end with pdf extension
			List<String> result = walk.map(x -> x.toString())
					.filter(s -> s.endsWith("pdf"))
					.collect(Collectors.toList());

			result.forEach(x -> System.out.println(x.substring(x.lastIndexOf("\\") + 1)));
			*//*

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException thrown!!");
		}
		*/

		/*
		String testPath = "C:\\\\Users\\\\1994\\\\Documents\\\\Personal";
		Path rootLocation = Paths.get("C:\\\\Users\\\\1994\\\\Documents\\\\Personal\\\\Docs");

		try {
			Stream<Path> walk = Files.walk(rootLocation)
					.filter(path -> !path.equals(rootLocation))
					.map(path -> rootLocation.relativize(path));

			List<String> pathList = walk.map(path -> path.toString())
					.collect(Collectors.toList());

			pathList.forEach(System.out::println);

		} catch (IOException e) {

		}
		*/

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		String newstring = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(new Date());
		System.out.println("Date Format: " + newstring);

		SpringApplication.run(LearnReactiveSpringApplication.class, args);
	}

}
