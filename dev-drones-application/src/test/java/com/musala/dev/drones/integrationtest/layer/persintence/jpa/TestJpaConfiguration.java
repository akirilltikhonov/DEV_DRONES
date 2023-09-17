package com.musala.dev.drones.integrationtest.layer.persintence.jpa;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan({
        "com.musala.dev.drones.infra.persistence.jpa",
})
public class TestJpaConfiguration {
}
