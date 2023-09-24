package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneService;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
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
    public List<Drone> findAvailableDronesForLoading() {
        var filter = DroneFilter.builder()
                .minBatteryLevel(25)
                .states(List.of(State.IDLE))
                .build();
        return droneRepository.findByFilter(filter);
    }

    @Override
    public Integer getBatteryLevel(String serialNumber) {
        var drone = droneRepository.findBySerialNumber(serialNumber);
        return drone.getBatteryLevel();
    }
}
