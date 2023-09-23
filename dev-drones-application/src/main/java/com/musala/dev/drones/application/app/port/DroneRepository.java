package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Drone;

import java.util.List;

public interface DroneRepository {

    Drone register(Drone drone);

    Drone findBySerialNumber(String serialNumber);

    List<String> findAvailableDronesForLoading();

    List<Drone> findDrones(Integer lessThanBatteryLevel, Integer numberOfDrones);
}
