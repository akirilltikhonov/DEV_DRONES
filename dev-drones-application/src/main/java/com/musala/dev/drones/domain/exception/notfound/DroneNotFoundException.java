package com.musala.dev.drones.domain.exception.notfound;

public class DroneNotFoundException extends DataNotFoundException {

    public DroneNotFoundException(String serialNumber) {
        super(String.format("Drone not found by serialNumber = %s", serialNumber));
    }
}