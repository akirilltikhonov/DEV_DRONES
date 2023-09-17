package com.musala.dev.drones.infra.api.rest.controller;

import com.musala.dev.drones.app.port.DroneRepository;
import com.musala.dev.drones.domain.model.Drone;
import com.musala.dev.drones.dto.DroneDto;
import com.musala.dev.drones.dto.RegisterDroneDto;
import com.musala.dev.drones.infra.api.rest.mapper.DroneRequestMapper;
import com.musala.dev.drones.infra.api.rest.mapper.DroneResponseMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @InjectMocks
    private DroneController droneController;
    @Mock
    private DroneRequestMapper droneRequestMapper;
    @Mock
    private DroneResponseMapper droneResponseMapper;
    @Mock
    private DroneRepository droneRepository;

    private final EasyRandom random = new EasyRandom();

    @Test
    void register() {
        var registerDroneDto = random.nextObject(RegisterDroneDto.class);

        var droneToRegister = random.nextObject(Drone.class);
        doReturn(droneToRegister).when(droneRequestMapper).toDrone(registerDroneDto);
        var registeredDrone = random.nextObject(Drone.class);
        doReturn(registeredDrone).when(droneRepository).register(droneToRegister);
        var droneDto = random.nextObject(DroneDto.class);
        doReturn(droneDto).when(droneResponseMapper).toDroneDto(registeredDrone);

        assertThat(droneController.register(registerDroneDto)).isEqualTo(ResponseEntity.ok(droneDto));
    }
}