package com.musala.dev.drones.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.app.port.DroneRepository;
import com.musala.dev.drones.domain.model.Drone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DroneRepositoryImpl implements DroneRepository {

    @Override
    public Drone register(Drone drone) {
        return null;
    }
}
