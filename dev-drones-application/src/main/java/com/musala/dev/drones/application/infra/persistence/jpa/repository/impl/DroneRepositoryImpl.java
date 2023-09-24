package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.domain.exception.notfound.DroneNotFoundException;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Drone findToLoadMedication(String serialNumber) {
        var droneEntity = droneJpaRepository.findBySerialNumberAndState(serialNumber, State.IDLE)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber, State.IDLE));
        droneEntity.setState(State.LOADING);
        return droneMapper.toDrone(droneEntity);
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        var droneEntity = droneJpaRepository.findById(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber));
        return droneMapper.toDrone(droneEntity);
    }

    @Override
    public List<Drone> findByFilter(DroneFilter filter) {
        var droneEntity = droneJpaRepository.findByFilter(filter);
        return droneMapper.toDrones(droneEntity);
    }

    @Override
    public List<Drone> findDrones(Integer batteryLevelLessThan, Integer numberOfDrones) {
        var droneEntities = droneJpaRepository
                .findByBatteryLevelIsLessThanEqual(batteryLevelLessThan, PageRequest.of(0, numberOfDrones));
        return droneMapper.toDrones(droneEntities);
    }
}
