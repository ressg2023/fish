package com.example.fishing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FishingApplication {

	public static void main(String[] args) {
		log.info("Starting...");
		SpringApplication.run(FishingApplication.class, args);
		log.info("End...");
	}
}
