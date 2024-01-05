package com.example.springboot.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "vehicle")
public class Vehicle {
  @Id
  private String name;
  private String wheels;

  // Getter for name
  public String getName() {
      return this.name;
  }

  // Setter for name
  public void setName(String name) {
      this.name = name;
  }

  // Getter for name
  public String getWheels() {
      return this.wheels;
  }

  // Setter for name
  public void setWheels(String wheels) {
    if (wheels.matches("\\d+")) {
      this.wheels = wheels;
    } else {
      throw new RuntimeException("Wheels should be number, received: " + wheels);
    }
  }
}
