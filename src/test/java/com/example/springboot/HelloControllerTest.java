package com.example.springboot;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void simpleGETAPIShouldReturnGreetingMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Greetings from Spring Boot for GET!!!");
    }

    @Test
    void simplePOSTAPIShouldReturnGreetingMessage() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/", "{\"test\": \"Abc\"}",
                String.class)).contains("Greetings from Spring Boot for POST with body::{\"test\": \"Abc\"}");
    }
}