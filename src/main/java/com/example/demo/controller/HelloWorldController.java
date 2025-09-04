package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/")
	public String home() {
		return "Hello World from Spring Boot!";
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello World! This is a CI/CD Pipeline Demo";
	}

	@GetMapping("/health")
	public String health() {
		return "Application is running successfully!";
	}
}