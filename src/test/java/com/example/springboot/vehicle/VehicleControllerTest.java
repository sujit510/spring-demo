package com.example.springboot.vehicle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {
    @Autowired
    private VehicleController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

//    @Test
//    void shouldReturnAllVehicles() throws Exception {
//        this.mockMvc.perform(get("/api/entities")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().json({})));
//    }
//
    @Test
    void simplePOSTAPIShouldReturnGreetingMessage() throws Exception {
        this.mockMvc.perform(
                        post("/")
                                .content("{\"test\": \"Abc\"}")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("Greetings from Spring Boot for POST with body::{\"test\": \"Abc\"}"));
    }
}
