package com.reactivespring;

import com.reactivespring.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class LearnReactiveSpringApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LearnReactiveSpringApplication.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(LearnReactiveSpringApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		logger.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, " +
				"first_name VARCHAR(255), last_name VARCHAR(255))");

		// Split the array of names into first/last names
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean").stream()
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		splitUpNames.forEach(name -> logger.info("Inserting record for %s %s", name[0], name[1]));

		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) " +
				"VALUES (?, ?)", splitUpNames);

		logger.info("Querying if a user: Jeff is present");

		jdbcTemplate.query("select id, first_name, " +
				"last_name FROM customers where first_name=?",
				new Object[]{"John"},
				(rs, rowNum) -> new Customer(rs.getLong("id"),
						rs.getString("first_name"),
						rs.getString("last_name")))
				.forEach(customer -> logger.info(customer.toString()));

	}
}
