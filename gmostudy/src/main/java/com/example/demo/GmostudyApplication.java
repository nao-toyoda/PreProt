package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.controller"})
public class GmostudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmostudyApplication.class, args);
	}
}
