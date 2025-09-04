package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.HelloWorldController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHome() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string("Hello World from Spring Boot!"));
	}

	@Test
	public void testHello() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
				.andExpect(content().string("Hello World! This is a CI/CD Pipeline Demo"));
	}

	@Test
	public void testHealth() throws Exception {
		mockMvc.perform(get("/health")).andExpect(status().isOk())
				.andExpect(content().string("Application is running successfully!"));
	}
}