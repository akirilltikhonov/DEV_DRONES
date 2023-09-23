package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    @Override
    public Integer getBatteryLevel(String serialNumber) {
        var drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getBatteryCapacity();
    }
}
