package com.reactivespring;

import com.reactivespring.model.Customer;
import com.reactivespring.model.ResponseStructure;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.*;
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

		String test = null;

		ResponseStructure responseStructure = new ResponseStructure();

		if(StringUtils.isEmpty(test)) {
			logger.info("String is empty");
		} else {
			logger.info("Given string is not empty");
		}

		Optional<Object> optionalObject = null;

		if(!StringUtils.isEmpty(optionalObject) && optionalObject.isPresent()) {
			logger.info("Optional is not null.");
		} else {
			logger.info("Optional is null.");
		}

		List<String> testList = new ArrayList<>();
		testList.add("test1");
		testList.add("test2");
		testList.add("test3");

		displayTest(testList);

		testList.forEach(ele -> System.out.println("List value: " + ele));

		String response = "{\n" +
				"  \"status\": \"Success\",\n" +
				"  \"errorCode\": 0,\n" +
				"  \"data\": \"{\\\"document_identifier\\\": \\\"Form GST REG-06\\\", \\\"registration_number\\\": \\\"07AABFU1103F1ZT\\\", \\\"legal_name\\\": \\\"UNIVERSAL FASHIONS \\\", \\\"trade_name\\\": \\\"UNIVERSAL FASHIONS \\\", \\\"business_constitution\\\": \\\"Partnership \\\", \\\"principal_address\\\": \\\"Basement, First Floor, C-200, PHASE-I, NARAINA  INDUSTRIAL AREA, South West Delhi, Delhi, 110028 \\\", \\\"registration_type\\\": \\\"Regular \\\", \\\"certificate_issue_date\\\": \\\"16/07/2018 \\\", \\\"gstin\\\": \\\"07AABFU1103F1ZT \\\", \\\"key_persons\\\": [{\\\"name\\\": \\\"\\\", \\\"designation\\\": \\\"\\\", \\\"resident\\\": \\\"\\\"}, {\\\"name\\\": \\\"\\\", \\\"designation\\\": \\\"\\\", \\\"resident\\\": \\\"\\\"}, {\\\"name\\\": \\\"\\\", \\\"designation\\\": \\\"\\\", \\\"resident\\\": \\\"\\\"}]}\",\n" +
				"  \"requestId\": \"\",\n" +
				"  \"statusCode\": 0\n" +
				"}";

		JSONObject jsonObject = new JSONObject(response);
		JSONObject jsonObject1 = new JSONObject(jsonObject.getString("data"));

		System.out.println("Json: " + jsonObject1.getString("gstin").substring(2, 12));

		System.out.println("PAN Number: " + response);
		response = null;

		System.out.println("Objects not null: " + Objects.nonNull(response));

		System.out.println("***************************");

		String responseTest = "{\n" +
				"  \"status\": \"Success\",\n" +
				"  \"errorCode\": 0,\n" +
				"  \"data\": {\n" +
				"    \"data\": {\n" +
				"      \"gst_onboarding_type\": null,\n" +
				"      \"bilId\": \"8ea201f7-5611-4ecf-bac9-f7136e33655b\",\n" +
				"      \"pan\": \"ASDPC6526E\",\n" +
				"      \"onboarding_type\": \"non-gst\",\n" +
				"      \"target\": \"PAN_UPLOAD\"\n" +
				"    },\n" +
				"    \"requestId\": \"\",\n" +
				"    \"errorCode\": 0,\n" +
				"    \"message\": null,\n" +
				"    \"status\": \"Success\",\n" +
				"    \"statusCode\": 0\n" +
				"  },\n" +
				"  \"requestId\": \"\",\n" +
				"  \"statusCode\": 200,\n" +
				"  \"message\": null\n" +
				"}";

		JSONObject jsonObjectTest = new JSONObject(responseTest);

//		Map<String, Object> dataJson = (Map<String, Object>)jsonObjectTest.get("data");
//		System.out.println("dataJson: " + jsonObjectTest.getJSONObject("data").getJSONObject("data").getString("bilId"));

//		System.out.println("responseTest: " + jsonObjectTest.getJSONObject("data"));

//		String responseData = jsonObjectTest.ge

		Map<String, Object> testDataMap1 = null;
		Map<String, Object> testDataMap2 = null;

		if(StringUtils.isEmpty(jsonObjectTest.get("data"))) {
			responseStructure.setMessage(null);
		} else {

			try {
				testDataMap1 = jsonObjectTest.getJSONObject("data").toMap();

				try {
					testDataMap2 = jsonObjectTest.getJSONObject("data").getJSONObject("data").toMap();
					responseStructure.setMessage(testDataMap2);
				} catch (JSONException e) {
					responseStructure.setMessage(testDataMap1);
				}

			} catch (JSONException e) {
				responseStructure.setMessage(jsonObjectTest.get("data").toString());
			}
		}

		System.out.println("response Data structure: " + responseStructure.getMessage());

		System.out.println("***************************");

		System.out.println("***************************");

		System.out.println("Bad request: " + HttpStatus.BAD_REQUEST.value() + ", Null request value: " + StringUtils.isEmpty(response));

		try {
			display();
		} catch (Exception e) {
			logger.error("display1: " + e.getMessage());
		}


		System.out.println("***************************");

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

	public void display() throws Exception{
//		try {
//			throw new Exception("display2");
//		} catch (Exception e) {
//			logger.error("display2: " + e.getMessage());
//			throw new RuntimeException("display23");
//		}

		throw new Exception("display2");
	}

	public void displayTest(List<String> tests) {
		tests.add("test4");
	}

}
