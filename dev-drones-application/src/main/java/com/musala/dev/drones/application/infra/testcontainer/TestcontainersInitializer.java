package com.musala.dev.drones.application.infra.testcontainer;

import com.musala.dev.drones.application.infra.testcontainer.postgres.PostgresTestcontainerInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class TestcontainersInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        new PostgresTestcontainerInitializer()
                .initialize(context);
    }
}
