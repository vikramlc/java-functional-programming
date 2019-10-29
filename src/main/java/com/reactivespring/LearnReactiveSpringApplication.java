package com.reactivespring;

import com.reactivespring.consumerestservice.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LearnReactiveSpringApplication {

	private static final Logger logger = LoggerFactory.getLogger(LearnReactiveSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearnReactiveSpringApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

		return args -> {

			Quote quote = restTemplate
					.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			logger.info(quote.toString());

		};

	}

}
