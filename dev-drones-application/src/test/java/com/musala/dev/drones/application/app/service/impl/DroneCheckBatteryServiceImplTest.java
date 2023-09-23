package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.domain.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DroneCheckBatteryServiceImplTest {

    private DroneCheckBatteryServiceImpl droneCheckBatteryService;
    private final int minBatteryLevelToLog = 50;
    private final int numberOfDrones = 10;
    private final DroneRepository droneRepository = mock(DroneRepository.class);

    @BeforeEach
    void setUp() {
        droneCheckBatteryService = new DroneCheckBatteryServiceImpl(
                minBatteryLevelToLog,
                numberOfDrones,
                droneRepository
        );
    }

    @Test
    void checkDronesBatteryLevelNotIncreaseDelay() {
        var drones = List.of(Drone.builder().build());
        doReturn(drones).when(droneRepository).findDrones(minBatteryLevelToLog, numberOfDrones);

        assertThat(droneCheckBatteryService.checkDronesBatteryLevel())
                .isFalse();
    }

    @Test
    void checkDronesBatteryLevelIncreaseDelay() {
        assertThat(droneCheckBatteryService.checkDronesBatteryLevel())
                .isTrue();
    }
}