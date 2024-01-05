package com.example.springboot.vehicle;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldRestrictCreatingNonNumericWheels() throws Exception {
        String inputWheels = "abcd";
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> this.vehicle.setWheels(inputWheels),
                "Expected to be restricted from setting non numeric wheels (" + inputWheels + "), but it didn't."
        );
        assertTrue(thrown.getMessage().equals("Wheels should be number, received: " + inputWheels));
    }
}
