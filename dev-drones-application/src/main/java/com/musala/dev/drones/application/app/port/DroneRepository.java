package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Drone;

public interface DroneRepository {

    Drone register(Drone drone);

    Drone findBySerialNumber(String serialNumber);
}
