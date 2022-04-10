package com.citi.FeedGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class FeedGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedGeneratorApplication.class, args);
	}

}
