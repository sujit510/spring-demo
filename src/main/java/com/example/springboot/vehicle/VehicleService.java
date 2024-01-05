package com.example.springboot.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> getAllEntities() {
        return repository.findAll();
    }
    
    public Vehicle addVehicle(Vehicle vehicle) {
        if (!vehicle.getWheels().matches("\\d+")) {
            throw new RuntimeException("Wheels should be number, received: " + vehicle.getWheels());
        }
        return repository.save(vehicle);
    }

    public Vehicle updateVehicle(String name, Vehicle updatedVehicle) {
      return repository.findById(name).map(existingVehicle -> {
          // Update the necessary fields of the existingEntity
          existingVehicle.setWheels(updatedVehicle.getWheels());
          // More updates as needed
          return repository.save(existingVehicle);
      }).orElseThrow(() -> new RuntimeException("Vehicle with name " + name + " not found"));
    }

    public void deleteVehicle(String name) {
        if (repository.existsById(name)) {
            repository.deleteById(name);
        } else {
            throw new RuntimeException("Vehicle with name " + name + " not found.");
        }
    }
 }
