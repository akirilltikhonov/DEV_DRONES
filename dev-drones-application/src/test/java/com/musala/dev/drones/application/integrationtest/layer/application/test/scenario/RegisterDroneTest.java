package com.musala.dev.drones.application.integrationtest.layer.application.test.scenario;

import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.api.dto.enums.Model;
import com.musala.dev.drones.api.dto.enums.State;
import com.musala.dev.drones.application.infra.api.rest.controller.DroneController;
import com.musala.dev.drones.application.integrationtest.layer.application.ApplicationTest;
import org.assertj.core.api.Assertions;
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
                .batteryLevel(100)
                .build();

        var response = droneController.register(request);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        var drone = Objects.requireNonNull(response.getBody());
        assertThat(request)
                .usingRecursiveComparison()
                .isEqualTo(drone);
        Assertions.assertThat(drone.state()).isEqualTo(State.IDLE);
    }
}