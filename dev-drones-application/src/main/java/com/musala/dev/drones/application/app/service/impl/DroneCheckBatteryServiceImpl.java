package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneCheckBatteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DroneCheckBatteryServiceImpl implements DroneCheckBatteryService {

    @Value("${drone-executor.min-battery-level-to-log}")
    private final int minBatteryLevelToLog;
    @Value("${drone-executor.drones-per-request}")
    private final int numberOfDrones;
    private final DroneRepository droneRepository;

    @Override
    public boolean checkDronesBatteryLevel() {
        var drones = droneRepository.findDrones(minBatteryLevelToLog, numberOfDrones);
        return drones.isEmpty();
    }
}
