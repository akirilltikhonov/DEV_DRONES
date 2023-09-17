package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan({
        "com.musala.dev.drones.application.infra.persistence.jpa",
})
public class TestJpaConfiguration {
}
