package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.domain.exception.notfound.DroneNotFoundException;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DroneRepositoryImpl implements DroneRepository {

    private final DroneJpaRepository droneJpaRepository;
    private final DroneMapper droneMapper;

    @Override
    public Drone register(Drone drone) {
        var droneEntity = droneMapper.toDroneEntity(drone);
        return droneMapper.toDrone(droneJpaRepository.save(droneEntity));
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        var droneEntity = droneJpaRepository.findById(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber));
        return droneMapper.toDrone(droneEntity);
    }

    @Override
    public List<String> findAvailableDronesForLoading() {
        var filter = DroneFilter.builder()
                .minBatteryLevel(25)
                .states(List.of(State.IDLE))
                .build();
        var droneEntity = droneJpaRepository.findByFilter(filter);
        return droneEntity.stream()
                .map(DroneEntity::getSerialNumber)
                .toList();
    }
}
