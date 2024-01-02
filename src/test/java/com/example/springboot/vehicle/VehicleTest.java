package com.example.springboot.vehicle;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    @Autowired
    Vehicle vehicle = spy(new Vehicle());

    @Test
    void contextLoads() throws Exception {
        assertThat(vehicle).isNotNull();
    }

    @Test
    void shouldSetAndGetNameInVehicleClass() throws Exception {
        this.vehicle.setName("aeroplane");
        assertEquals("aeroplane", this.vehicle.getName());
    }

    @Test
    void shouldSetAndGetWheelsInVehicleClass() throws Exception {
        this.vehicle.setWheels("20");
        assertEquals("20", this.vehicle.getWheels());
    }
}
