package com.example.testovoe_birthday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestovoeBirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestovoeBirthdayApplication.class, args);
	}

}
