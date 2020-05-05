package com.reactivespring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@SpringBootApplication
public class LearnReactiveSpringApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LearnReactiveSpringApplication.class);



//	@Bean
//	RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
//											MessageListenerAdapter listenerAdapter) {
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(redisConnectionFactory);
//		container.addMessageListener(listenerAdapter, new PatternTopic("chat")		);
//		return container;
//	}
//
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}
//
//	@Bean
//	Receiver receiver(CountDownLatch latch) {
//		return new Receiver(latch);
//	}
//
//	@Bean
//	CountDownLatch latch() {
//		return new CountDownLatch(1);
//	}
//
//	@Bean
//	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
//		return new StringRedisTemplate(connectionFactory);
//	}

    private static boolean notEmpty(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

	public static void main(String[] args) {

		System.out.println("Starting execution");

        System.out.println("********************************");

        String pan1 = "";
        String pan2 = null;

        if(StringUtils.isEmpty(pan1)) {
            System.out.println("PAN1 is empty!!");
        }

        if(StringUtils.isEmpty(pan2)) {
            System.out.println("PAN2 is empty!!");
        }

		System.out.println("********************************");

        DateCalculator dateCalculator = new DateCalculator();
        dateCalculator.calculate();

        System.out.println("********************************");

		String gstInvalidGstResponse = "{\n" +
				"    \"message\": \"Error in Extracting Information!\",\n" +
				"    \"s3\": {\n" +
				"        \"bucket\": \"solvezy-dev-ocr-applog\",\n" +
				"        \"key\": \"gst/3003729882278774_file\",\n" +
				"        \"versionId\": null,\n" +
				"        \"message\": \"File Upload Successful\"\n" +
				"    }\n" +
				"}\n";

		JsonNode attributes = null;
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			node = mapper.readTree(gstInvalidGstResponse);
			attributes = node.get("attributes");
		} catch (JsonProcessingException e) {
			System.out.println("This is json mapping exception.");
		}

		if(node.get("attributes") == null) {
			System.out.println("Attributes is null!!");
		}

		System.out.println("Attribute json: " + attributes);

		System.out.println("********************************");

		ArrayList<String> testList = new ArrayList<>();
		testList.add("others/1644837319259962_itr1.pdf");
		testList.add("others/1644837319259962_itr2.pdf");
		testList.add("others/1644837319259962_itr3.pdf");
		testList.add("others/1644837319259962_itr4.pdf");
		testList.add("solvezy-dev-ocr-applog");
		testList.add("solvezy-dev-ocr-applog");

        List<String> stringList = testList.subList(testList.size() - 3, testList.size());

        List<String> fileNames = stringList.stream().filter(res -> res.startsWith("others"))
                .map(res -> res.substring(res.indexOf("_") + 1))
                .collect(Collectors.toList());

        fileNames.forEach(System.out::println);

        System.out.println("--------------------------------------");

        String exampleJson = "{\n" +
                "  \"status\": \"Success\",\n" +
                "  \"errorCode\": 0,\n" +
                "  \"data\": {\n" +
                "    \"solvId\": \"3LBZKX\",\n" +
                "    \"customerName\": \" GANAPATHI  BHAT \",\n" +
                "    \"gstNo\": \"29AGGPB1329M1ZD\",\n" +
                "    \"panNo\": \"AGGPB1329M\",\n" +
                "    \"cinLlpin\": null,\n" +
                "    \"companyStatus\": null,\n" +
                "    \"rocCode\": null,\n" +
                "    \"companyEmailId\": null,\n" +
                "    \"companyMobileNumber\": null,\n" +
                "    \"emailId\": \"sudhanshu.gupta@solvezy.com\",\n" +
                "    \"mobileNumber\": \"+918105109504\",\n" +
                "    \"dateOfIncorporation\": null,\n" +
                "    \"docType\": null,\n" +
                "    \"s3Bucket\": null,\n" +
                "    \"s3Key\": null,\n" +
                "    \"status\": null,\n" +
                "    \"applicationId\": null,\n" +
                "    \"customerId\": \"bff96da8-0f2a-4c21-b0f0-67784edec1e1\"\n" +
                "  },\n" +
                "  \"requestId\": \"\",\n" +
                "  \"statusCode\": 200,\n" +
                "  \"errorMessage\": null\n" +
                "}";

        JSONObject rawJson = new JSONObject(exampleJson);
        JSONObject customerDetails = rawJson.getJSONObject("data");
        if(!customerDetails.isEmpty() && customerDetails != null) {
            String docType = customerDetails.get("docType").toString();
            String onboardingBucket = customerDetails.get("s3Bucket").toString();
            String onboardingKey = customerDetails.get("s3Key").toString();
            String onboardingStatus = customerDetails.get("status").toString();

            if(!notEmpty(null)) {
                System.out.println("onboarding status is not empty/null.");
                System.out.println(customerDetails.getString("customerName").trim());
            }

            if(!StringUtils.isEmpty(docType) && !StringUtils.isEmpty(onboardingBucket) &&
                    !StringUtils.isEmpty(onboardingKey) && !StringUtils.isEmpty(onboardingStatus)) {
                if(docType.equalsIgnoreCase("pan") &&
                        onboardingStatus.equalsIgnoreCase("verified")) {
                    System.out.println(docType);
                    System.out.println(onboardingBucket);
                    System.out.println(onboardingKey);
                    System.out.println(customerDetails.getString("panNo"));
                } else if(docType.equalsIgnoreCase("GST") &&
                        onboardingStatus.equalsIgnoreCase("verified")) {
                    System.out.println(docType);
                    System.out.println(onboardingBucket);
                    System.out.println(onboardingKey);
                    System.out.println(customerDetails.getString("gstNo"));
                    System.out.println(customerDetails.getString("panNo"));
                }
            } else {
                LOGGER.error("DocType/OnboardingBucket/OnboardingKey/Onboarding status may be empty.");
            }
        }

        System.out.println("--------------------------------------");

        Person person1 = new Person("firstname1", "lastname1", 80f);
		Person person2 = new Person("2firstname2", "lastname2", 40f);
		Person person3 = new Person("firstname3", "lastname1", 20f);
		Person person4 = new Person("firstname4", "lastname4", 70f);

		List<Person> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		personList.add(person3);
		personList.add(person4);

		World world1 = new World("world1", personList);
		World world2 = new World("world2", personList);
		World world3 = new World("world3", personList);
		World world4 = new World("world4", personList);

		long count = personList.stream().filter(person -> person.getLastName() == "lastname4").count();
		System.out.println("Person count: " + count);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
		LocalDate creationLocalDate = LocalDate.parse("16-12-2019", formatter);
		System.out.println("CreationLocalDate: " + creationLocalDate.getDayOfMonth() + "_" +
				creationLocalDate.getMonth() + "_" +
				creationLocalDate.getYear());

		List<Person> personList1 = world1.getPersonList().stream()
				.filter(person -> person.getFirstName().startsWith("firstname"))
				.collect(Collectors.toList());

		personList1.forEach(person -> System.out.println(person.getFirstName()));

		/*
		// To create a file
		Path path = Paths.get("C:/Users/1994/Documents/TestFiles/file_create.txt");
		try {
			Path createdFilePath = Files.createFile(path);
			System.out.println("Files created at path: " + createdFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*
		// To read a file
		Path path = Paths.get("C:/Users/1994/Documents/TestFiles/file.txt");
		try {
			byte[] bytes = Files.readAllBytes(path);
			List<String> strings = Files.readAllLines(path);

			System.out.println("Read bytes: " + new String(bytes));
			System.out.println("Read lines: " + strings);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*
		// To write to a file
		Path path = Paths.get("C:/Users/1994/Documents/TestFiles/file_create.txt");
		String str = "This is example line to write to a file.";

		try {
			Path write = Files.write(path, str.getBytes());
			System.out.println("Content: " + new String(Files.readAllBytes(write)));
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*
		// To traverse a directory
		Path path = Paths.get("C:/Users/1994/Documents/TestFiles");

		try {
			Files.walkFileTree(path, new FileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println("Pre-visit directory: " + dir);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println("Visit file: " + file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					System.out.println("Visit file failed: " + file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					System.out.println("Post visit directory: " + dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		/*String dateStart = "1/12/2019";
		String dateStop = "31/12/2019";

		String[] insert1 = dateStart.split("/");
		String[] insert2 = dateStop.split("/");

		System.out.println("Start date: " + insert1[0] + insert1[1] + insert1[2]);
		System.out.println("Stop date: " + insert2[0] + insert2[1] + insert2[2]);

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(insert1[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(insert1[1]));
		cal.set(Calendar.YEAR, Integer.parseInt(insert1[2]));
		Date firstDate = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(insert2[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(insert2[1]));
		cal.set(Calendar.YEAR, Integer.parseInt(insert2[2]));
		Date secondDate = cal.getTime();

		long diff = secondDate.getTime() - firstDate.getTime();

		System.out.println ("Days: " + diff / 1000 / 60 / 60 / 24);*/

		/*LocalDate startDate = LocalDate.of(2019, 12, 1);
		LocalDate stopDate = LocalDate.of(2020, 1, 31);

		long daysBetween = ChronoUnit.DAYS.between(startDate, stopDate) + 1;

		LocalDate testDate = LocalDate.of(2020, 2, 01);

		if(testDate.equals(startDate) || testDate.equals(stopDate) ||
				testDate.isAfter(startDate) && testDate.isBefore(stopDate)) {
			System.out.println("between date range.");
		}

		System.out.println("Days between: " + daysBetween);*/

		/*// For from date and to date logic
		List<DateInterval> dateRangeList = new ArrayList<>();
		dateRangeList.add(new DateInterval(LocalDate.of(2012, 5, 1),
				LocalDate.of(2012, 7, 1)));
		dateRangeList.add(new DateInterval(LocalDate.of(2012, 7, 2),
				LocalDate.of(2012, 9, 5)));
		dateRangeList.add(new DateInterval(LocalDate.of(2012, 1, 1),
				LocalDate.of(2012, 3, 31)));
		dateRangeList.add(new DateInterval(LocalDate.of(2012, 8, 1),
				LocalDate.of(2012, 12, 31)));
		dateRangeList.add(new DateInterval(LocalDate.of(2012, 4, 1),
				LocalDate.of(2012, 4, 30)));
		dateRangeList.add(new DateInterval(LocalDate.of(2011, 12, 1),
				LocalDate.of(2011, 12, 31)));

		LocalDate testDate = LocalDate.of(2012, 12, 31);

		LocalDate lastYearDate = LocalDate.of(testDate.getYear()-1, testDate.getMonth(), 01);

		System.out.println("Date last year: " + lastYearDate.toString());

		mergeIntervals(dateRangeList, testDate, lastYearDate);*/

		/*List<LocalDate> localDateStartList = new ArrayList<>();
		localDateStartList.add(LocalDate.of(2012, 5, 1));
		localDateStartList.add(LocalDate.of(2012, 7, 2));
		localDateStartList.add(LocalDate.of(2012, 5, 1));
		localDateStartList.add(LocalDate.of(2012, 8, 1));

		List<LocalDate> dateStartList = localDateStartList.stream().sorted().collect(Collectors.toList());

		List<LocalDate> localDateEndList = new ArrayList<>();
		localDateEndList.add(LocalDate.of(2012, 3, 31));
		localDateEndList.add(LocalDate.of(2012, 7, 1));
		localDateEndList.add(LocalDate.of(2012, 9, 5));
		localDateEndList.add(LocalDate.of(2012, 12, 31));

		List<LocalDate> dateEndList = localDateEndList.stream().sorted().collect(Collectors.toList());

		System.out.println("Printing sorted start dates: ");

		dateStartList.forEach(date -> {
			System.out.println(date);
		});

		System.out.println();

		System.out.println("Printing sorted end dates: ");

		dateEndList.forEach(date -> {
			System.out.println(date);
		});*/

		/*dateStartList.forEach(startDate -> {
			LocalDate tempStartDate = startDate;
			dateEndList.forEach(endDate -> {
				LocalDate tempEndDate = endDate;

				if(startDate.equals(endDate) || startDate.isBefore(endDate)) {

				}

			});

		});*/

		SpringApplication.run(LearnReactiveSpringApplication.class, args);

//		ApplicationContext ctx = SpringApplication.run(LearnReactiveSpringApplication.class, args);
//		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//		CountDownLatch latch = ctx.getBean(CountDownLatch.class);
//
//		LOGGER.info("Sending messages...");
//		template.convertAndSend("chat", "Hello from Redis!");
//
//		latch.await();
//
//		System.exit(0);
	}

	private static void mergeIntervals(List<DateInterval> dateRangeList, LocalDate updatedDate, LocalDate lastYearDate) {

		if(!dateRangeList.isEmpty()) {
			Stack<DateInterval> intervalStack = new Stack<>();
			List<DateInterval> dateIntervalList = dateRangeList.stream().sorted(new Comparator<DateInterval>() {
				@Override
				public int compare(DateInterval o1, DateInterval o2) {
					return o1.getStartDate().compareTo(o2.getStartDate());
				}
			}).collect(Collectors.toList());

			boolean first = true;

			for(DateInterval dateInterval: dateIntervalList) {
				if(first) {
					first = false;

					if(dateInterval.getStartDate().isBefore(lastYearDate)) {
						dateInterval.setStartDate(lastYearDate);
					} else if (dateInterval.getEndDate().isAfter(lastYearDate)) {
						// update the status in the document stating that 12 MONTHS STATEMENTS NOT AVAILABLE
					}

					intervalStack.push(dateInterval);
					continue;
				}

				if(dateInterval.getEndDate().isAfter(updatedDate)) {
					dateInterval.setEndDate(updatedDate);
				}

				DateInterval top = intervalStack.peek();

				if(top.getEndDate().isBefore(dateInterval.getStartDate())) {
					intervalStack.push(dateInterval);
				} else if(top.getEndDate().isBefore(dateInterval.getEndDate())) {
					top.setEndDate(dateInterval.getEndDate());
					intervalStack.pop();
					intervalStack.push(top);
				}
			}

			long sum = 0;
			long maxDays = ChronoUnit.DAYS.between(lastYearDate, updatedDate) + 1;

			System.out.println("Merge Intervals are: ");

			while(!intervalStack.empty()) {
				DateInterval dateInterval = intervalStack.pop();
				LocalDate startDate = dateInterval.getStartDate();
				LocalDate stopDate = dateInterval.getEndDate();

				System.out.println("["+dateInterval.getStartDate()+","+dateInterval.getEndDate()+"] ");

				long daysBetween = ChronoUnit.DAYS.between(startDate, stopDate) + 1;
				sum = sum + daysBetween;

//				if(updatedDate.equals(startDate) || updatedDate.equals(stopDate) ||
//						updatedDate.isAfter(startDate) && updatedDate.isBefore(stopDate)) {
//					System.out.println("between date range.");
//				}
			}

			if(sum == maxDays) {
				System.out.println("No missing months!!");
			} else {
				System.out.println("There are few month statements missing!!");
			}

		}

	}

}
