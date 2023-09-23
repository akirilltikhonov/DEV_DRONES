package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DroneRepositoryTest extends JpaTest {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneMapper droneMapper;
    @Autowired
    private DroneJpaRepository droneJpaRepository;

    @Test
    void register() {
        var registered = droneRepository.register(droneMapper.toDrone(DroneEntityGenerator.next()));
        var found = droneRepository.findBySerialNumber(registered.getSerialNumber());
        assertThat(registered)
                .isEqualTo(found);
    }

    @Test
    void findAvailableDronesForLoading() {
        var serialNumber1 = droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                        .batteryLevel(50)
                        .state(State.IDLE)
                        .build()))
                .getSerialNumber();
        var serialNumber2 = droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                        .batteryLevel(25)
                        .state(State.IDLE)
                        .build()))
                .getSerialNumber();
        droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                .batteryLevel(24)
                .state(State.IDLE)
                .build()));
        droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                .batteryLevel(50)
                .state(State.DELIVERED)
                .build()));

        var serialNumbers = droneRepository.findAvailableDronesForLoading();
        assertThat(serialNumbers).containsAll(List.of(serialNumber1, serialNumber2));
    }
}