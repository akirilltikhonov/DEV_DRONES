package com.musala.dev.drones.integrationtest.layer.application.test.scenario;

import com.musala.dev.drones.dto.RegisterDroneDto;
import com.musala.dev.drones.dto.enums.Model;
import com.musala.dev.drones.dto.enums.State;
import com.musala.dev.drones.infra.api.rest.controller.DroneController;
import com.musala.dev.drones.integrationtest.layer.application.ApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterDroneTest extends ApplicationTest {

    @Autowired
    private DroneController droneController;

    @Test
    void registerDrone() {
        var request = RegisterDroneDto.builder()
                .serialNumber(UUID.randomUUID().toString())
                .model(Model.LIGHTWEIGHT)
                .weightLimit(500)
                .batteryCapacity(100)
                .build();

        var response = droneController.register(request);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        var drone = Objects.requireNonNull(response.getBody());
        assertThat(request)
                .usingRecursiveComparison()
                .isEqualTo(drone);
        assertThat(drone.state()).isEqualTo(State.IDLE);
    }
}