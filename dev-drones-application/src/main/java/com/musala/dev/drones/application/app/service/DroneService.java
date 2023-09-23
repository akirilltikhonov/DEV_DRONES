package com.musala.dev.drones.application.app.service;

import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public interface DroneService {

    List<Medication> getLoadedMedications(String serialNumber);

    Integer getBatteryLevel(String serialNumber);

    boolean checkDronesBatteryLevel();
}
