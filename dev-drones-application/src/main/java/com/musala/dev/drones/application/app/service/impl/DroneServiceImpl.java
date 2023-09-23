package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneService;
import com.musala.dev.drones.application.domain.model.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public List<Medication> getLoadedMedications(String serialNumber) {
        var drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getMedications();
    }

    @Override
    public Integer getBatteryLevel(String serialNumber) {
        var drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getBatteryCapacity();
    }
}
