package com.reactivespring;

import ch.qos.logback.core.util.FileUtil;
import io.reactivex.Flowable;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class LearnReactiveSpringApplication {

	public static void main(String[] args) {

		Flowable.just("Hello World!!").subscribe(System.out::println);

		Set<String> set = new HashSet<>();
		set = null;

//		Iterator<String> stringIterator = set.iterator();
//		System.out.println(stringIterator);

		String testFileName = "test123.pdf";

		System.out.println("TestFileName: " + testFileName.substring(testFileName.lastIndexOf(".") + 1));

		JSONArray jsonArray = new JSONArray();
//		jsonArray.put("Test0");
//		jsonArray.put("Test1");
//		jsonArray.put("Test2");

		JSONArray jsonArray1 = new JSONArray();
//		jsonArray.put("Test10");
//		jsonArray.put("Test11");
//		jsonArray.put("Test12");



		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("testKey1", "testValue1");

		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("testKey2", "testValue2");

		jsonArray1.put(jsonObject1);
		jsonArray1.put(jsonObject2);

		jsonArray.put(jsonArray1);

		System.out.println("JsonArrayTest: " + jsonArray.toString());

		System.out.println("*****************************");

		String testJson = "{\n" +
				"\t\"EntityDetails\": {\n" +
				"\t\t\"individual/Owner\": [{\n" +
				"\t\t\t\"test1\": true\n" +
				"\t\t}]\n" +
				"\t}\n" +
				"}";

		JSONObject test1 = new JSONObject(testJson);
		test1.getJSONObject("EntityDetails").put("individual/Owner", jsonArray1);

		System.out.println("test1: " + test1);

		String testString = "pan/704915219657966_PAN_card_for_Cloud_computing.pdf";

		String finalString = "";

		if(testString.lastIndexOf("/") >= 0) {
			System.out.println("String has a forward slash.");
			finalString = testString.substring(testString.lastIndexOf("/") + 1);
		} else {
			finalString = testString;
		}

		System.out.println("FinalString: " + finalString);

		try {
			String testChar = (String) null;
			System.out.println("This is fine character!!");
		} catch (Exception e) {
			System.out.println("test char fail!!");
		}

		// Testing java 8 feature

		Set<StakeHolder> stakeHolders = new HashSet<>();
		StakeHolder s1 = new StakeHolder();
		s1.setConsumerBureauPulled(true);
		StakeHolder s2 = new StakeHolder();
		s2.setConsumerBureauPulled(true);
		StakeHolder s3 = new StakeHolder();
		s3.setConsumerBureauPulled(true);
		stakeHolders.add(s1);
		stakeHolders.add(s2);
		stakeHolders.add(s3);

		boolean testValue = stakeHolders.stream().allMatch(StakeHolder::isConsumerBureauPulled);

		System.out.println("testValue: Java 8: " + testValue);

		String testFileUtils = "pan/704915219657966_PAN_card_for_Cloud_computing.pdf";

		System.out.println("testFileUtils name: " + FilenameUtils.getExtension(testFileUtils));

		SpringApplication.run(LearnReactiveSpringApplication.class, args);
	}

}
