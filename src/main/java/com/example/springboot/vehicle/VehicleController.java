package com.example.springboot.vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public List<Vehicle> getAllEntities() {
        return service.getAllEntities();
    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        Vehicle addedVehicle = service.addVehicle(vehicle);
        return ResponseEntity.ok(addedVehicle);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Vehicle> updateEntity(@PathVariable String name, @RequestBody Vehicle updatedVehicle) {
        Vehicle vehicle = service.updateVehicle(name, updatedVehicle);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String name) {
        service.deleteVehicle(name);
        return ResponseEntity.ok("success");
    }
}
