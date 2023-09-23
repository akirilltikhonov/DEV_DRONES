package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;

import java.util.List;

public interface DroneRepository {

    Drone register(Drone drone);

    Drone findToLoadMedication(String serialNumber);

    Drone findBySerialNumber(String serialNumber);

    List<Drone> findByFilter(DroneFilter filter);

    List<Drone> findDrones(Integer lessThanBatteryLevel, Integer numberOfDrones);
}
