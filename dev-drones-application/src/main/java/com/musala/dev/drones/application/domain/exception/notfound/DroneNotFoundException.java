package com.musala.dev.drones.application.domain.exception.notfound;

import com.musala.dev.drones.application.domain.model.enums.State;

public class DroneNotFoundException extends DataNotFoundException {

    public DroneNotFoundException(String serialNumber, State state) {
        super(String.format("Drone not found by serialNumber = %s, state = %s", serialNumber, state));
    }

    public DroneNotFoundException(String serialNumber) {
        super(String.format("Drone not found by serialNumber = %s", serialNumber));
    }
}