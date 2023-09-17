package com.musala.dev.drones.integrationtest.layer.web;

import com.musala.dev.drones.infra.api.rest.controller.DroneController;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
@MockBean({
        DroneController.class,
})
public class TestWebConfiguration {
}
