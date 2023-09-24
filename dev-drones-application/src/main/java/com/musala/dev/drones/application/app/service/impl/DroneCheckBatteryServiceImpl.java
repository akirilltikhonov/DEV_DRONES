package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.app.port.repository.EventRepository;
import com.musala.dev.drones.application.app.service.DroneCheckBatteryService;
import com.musala.dev.drones.application.domain.service.factory.event.EventFactory;
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
    private final EventFactory checkBatteryFactory;
    private final EventRepository eventRepository;

    @Override
    public boolean checkDronesBatteryLevel() {
        var drones = droneRepository.findDrones(minBatteryLevelToLog, numberOfDrones);
        if (drones.isEmpty()) {
            return true;
        } else {
            var events = checkBatteryFactory.buildEvents(drones);
            eventRepository.logEvents(events);
            return false;
        }
    }
}
