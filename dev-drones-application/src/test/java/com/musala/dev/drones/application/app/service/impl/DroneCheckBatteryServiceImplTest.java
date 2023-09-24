package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.app.port.repository.EventRepository;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.domain.service.factory.event.EventFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DroneCheckBatteryServiceImplTest {

    private DroneCheckBatteryServiceImpl droneCheckBatteryService;
    private final int minBatteryLevelToLog = 50;
    private final int numberOfDrones = 10;
    private final DroneRepository droneRepository = mock(DroneRepository.class);
    private final EventFactory checkBatteryFactory = mock(EventFactory.class);
    private final EventRepository eventRepository = mock(EventRepository.class);

    @BeforeEach
    void setUp() {
        droneCheckBatteryService = new DroneCheckBatteryServiceImpl(
                minBatteryLevelToLog,
                numberOfDrones,
                droneRepository,
                checkBatteryFactory,
                eventRepository
        );
    }

    @Test
    void checkDronesBatteryLevelNotIncreaseDelay() {
        var drones = List.of(Drone.builder().serialNumber("1").build());
        doReturn(drones).when(droneRepository).findDrones(minBatteryLevelToLog, numberOfDrones);
        var events = List.of(Event.builder().eventId(UUID.randomUUID()).build());
        doReturn(events).when(checkBatteryFactory).buildEvents(drones);

        assertThat(droneCheckBatteryService.checkDronesBatteryLevel())
                .isFalse();
        verify(eventRepository).logEvents(events);
    }

    @Test
    void checkDronesBatteryLevelIncreaseDelay() {
        assertThat(droneCheckBatteryService.checkDronesBatteryLevel())
                .isTrue();
    }
}