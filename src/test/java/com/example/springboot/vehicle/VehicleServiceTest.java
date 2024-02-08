package com.example.springboot.vehicle;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class VehicleServiceTest {

    // Use @InjectMocks to automatically reset service for each test,
    // else manually set it by using annotation @AutoWIred instead of @InjectMocks to declare service and using @BeforeEach to
    // reset repository and setting service instance with this
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

//    @BeforeEach
//    public void setUp() {
//        // Reset the mock before each test
//        Mockito.reset(vehicleRepository);
//        vehicleService = new VehicleService(vehicleRepository);
//
//    }
    @Test
    public void testGetAllEntities() {
        // Create a new entity.
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test2");
        vehicle.setWheels("8");
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("Test3");
        vehicle2.setWheels("10");
        List<Vehicle> vehicles = Arrays.asList(vehicle, vehicle2);

        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle2)).thenReturn(vehicle2);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        vehicleService.addVehicle(vehicle);
        vehicleService.addVehicle(vehicle2);
        List<Vehicle> actualVehicles = vehicleService.getAllEntities();

        // Check if the sizes of the lists are equal
        assertEquals(vehicles.size(), actualVehicles.size(), "The size of the lists should be equal");

        // Iterate through the lists and compare the properties of each Vehicle object
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle expectedVehicle = vehicles.get(i);
            Vehicle actualVehicle = actualVehicles.get(i);

            assertEquals(expectedVehicle.getName(), actualVehicle.getName(), "Names should be equal");
            assertEquals(expectedVehicle.getWheels(), actualVehicle.getWheels(), "Number of wheels should be equal");
        }
    }

    @Test
    public void testAddVehicleWIthValidValue() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test2");
        vehicle.setWheels("1");

        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle newVehicle = vehicleService.addVehicle(vehicle);

        assertNotNull(newVehicle);

        verify(vehicleRepository).save(vehicle);

    }
    @Test
    public void testAddVehicleWIthInvalidValue() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Test2");

        // Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            vehicle.setWheels("xyz");
            vehicleService.addVehicle(vehicle);
        });

        // Optionally assert the message
        assertTrue(exception.getMessage().equals("Wheels should be number, received: xyz"));

    }

    @Test
    void updateVehicle_ExistingVehicle_UpdatesVehicle() {
        // Arrange
        String name = "testVehicle";
        Vehicle existingVehicle = new Vehicle();
        existingVehicle.setName(name);
        existingVehicle.setWheels("4");

        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setWheels("6");

        when(vehicleRepository.findById(name)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(existingVehicle); // Assuming save returns the updated vehicle

        // Act
        Vehicle result = vehicleService.updateVehicle(name, updatedVehicle);

        // Assert
        assertNotNull(result);
        assertEquals("6", result.getWheels());
        verify(vehicleRepository).save(existingVehicle);
    }

    @Test
    void updateVehicle_NonExistingVehicle_ThrowsRuntimeException() {
        // Arrange
        String name = "nonExistingVehicle";
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setWheels("6");

        when(vehicleRepository.findById(name)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> vehicleService.updateVehicle(name, updatedVehicle),
                "Expected updateVehicle to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Vehicle with name " + name + " not found"));
    }

    @Test
    void deleteVehicle_ExistingVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("MyVehicle");
        vehicle.setWheels("3");
        when(vehicleRepository.existsById("MyVehicle")).thenReturn(true);
        vehicleService.deleteVehicle("MyVehicle");
        verify(vehicleRepository).deleteById("MyVehicle");
    }

    @Test
    void deleteVehicle_ExistingVehicle_ThrowsRuntimeException() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("MyVehicle");
        vehicle.setWheels("3");
        when(vehicleRepository.existsById("MyVehicle111")).thenReturn(false);

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> vehicleService.deleteVehicle("MyVehicle111"),
                "Expected deleteVehicle to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Vehicle with name MyVehicle111 not found."));
    }
}
