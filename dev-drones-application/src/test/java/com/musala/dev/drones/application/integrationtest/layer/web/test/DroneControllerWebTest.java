package com.musala.dev.drones.application.integrationtest.layer.web.test;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.application.infra.api.rest.controller.DroneController;
import com.musala.dev.drones.application.integrationtest.layer.web.WebTest;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DroneControllerWebTest extends WebTest {

    @Autowired
    private DroneController controller;

    private final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
    );

    @Test
    void register() throws Exception {
        var registerDroneDto = random.nextObject(RegisterDroneDto.class)
                .toBuilder()
                .weightLimit(500)
                .batteryCapacity(100)
                .build();

        var droneDto = random.nextObject(DroneDto.class);
        doReturn(ResponseEntity.ok(droneDto)).when(controller)
                .register(registerDroneDto);

        String jsonRequest = objectMapper.writeValueAsString(registerDroneDto);
        String expectedResponse = objectMapper.writeValueAsString(droneDto);

        var response = perform("register", jsonRequest)
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertThat(response.getContentAsString()).isEqualTo(expectedResponse);
    }
}