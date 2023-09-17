package com.musala.dev.drones.app.port;

import com.musala.dev.drones.domain.model.Drone;

public interface DroneRepository {

    Drone register(Drone drone);

    Drone findBySerialNumber(String serialNumber);
}
