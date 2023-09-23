package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    void findByFilter() {
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

        var filter = DroneFilter.builder()
                .minBatteryLevel(25)
                .states(List.of(State.IDLE))
                .build();
        var serialNumbers = droneRepository.findByFilter(filter)
                .stream()
                .map(Drone::getSerialNumber)
                .toList();
        assertThat(serialNumbers).containsAll(List.of(serialNumber1, serialNumber2));
    }

    @Test
    void findDrones() {
        List<String> okSerialNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var okSerialNumber = droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                            .batteryLevel(75)
                            .build()))
                    .getSerialNumber();
            okSerialNumbers.add(okSerialNumber);
        }

        for (int i = 0; i < 15; i++) {
            droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                    .batteryLevel(35)
                    .build()));
        }

        var drones = droneRepository.findDrones(50, 10);

        assertThat(drones.size()).isEqualTo(10);
        drones.stream()
                .map(Drone::getSerialNumber)
                .collect(Collectors.toSet())
                .forEach(sn -> assertThat(okSerialNumbers.contains(sn)).isFalse());
    }
}