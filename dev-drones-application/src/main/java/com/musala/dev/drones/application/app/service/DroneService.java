package com.musala.dev.drones.application.app.service;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public interface DroneService {

    List<Medication> getLoadedMedications(String serialNumber);

    List<Drone> findAvailableDronesForLoading();

    Integer getBatteryLevel(String serialNumber);
}
