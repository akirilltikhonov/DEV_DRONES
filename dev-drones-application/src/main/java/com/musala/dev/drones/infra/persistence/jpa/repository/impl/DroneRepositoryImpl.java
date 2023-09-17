package com.musala.dev.drones.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.app.port.DroneRepository;
import com.musala.dev.drones.domain.exception.notfound.DroneNotFoundException;
import com.musala.dev.drones.domain.model.Drone;
import com.musala.dev.drones.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.infra.persistence.jpa.repository.DroneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
