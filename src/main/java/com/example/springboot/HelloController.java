package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot for GET!!!";
	}

	@PostMapping("/")
	public String handlePost(@RequestBody String reqBody) {
		return "Greetings from Spring Boot for POST with body::" + reqBody;
	}

}