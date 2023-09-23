package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.enums.State;

import java.util.List;
import java.util.Optional;

public interface DroneRepository {

    Drone register(Drone drone);

    Drone findToLoadMedication(String serialNumber);

    Drone findBySerialNumber(String serialNumber);

    List<String> findAvailableDronesForLoading();

    List<Drone> findDrones(Integer lessThanBatteryLevel, Integer numberOfDrones);
}
