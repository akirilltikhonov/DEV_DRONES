package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.domain.model.Drone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @InjectMocks
    private DroneServiceImpl droneService;
    @Mock
    private DroneRepository droneRepository;

    @Test
    void getBatteryLevel() {
        String serialNumber = UUID.randomUUID().toString();

        var drone = Drone.builder()
                .serialNumber(serialNumber)
                .batteryCapacity(50)
                .build();
        doReturn(drone).when(droneRepository).findBySerialNumber(serialNumber);

        assertThat(droneService.getBatteryLevel(serialNumber))
                .isEqualTo(drone.getBatteryCapacity());
    }
}