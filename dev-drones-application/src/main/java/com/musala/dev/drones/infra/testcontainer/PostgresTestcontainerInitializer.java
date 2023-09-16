package com.musala.dev.drones.infra.testcontainer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

public class PostgresTestcontainerInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        final var postgresContainer = StaticPostgresContainer.getContainer();
        final String jdbcUrl = postgresContainer.getJdbcUrl();
        var env = context.getEnvironment();
        env.getPropertySources().addFirst(new MapPropertySource(
                "testcontainers",
                Map.of(
                        "spring.datasource.url", jdbcUrl,
                        "spring.datasource.username", postgresContainer.getUsername(),
                        "spring.datasource.password", postgresContainer.getPassword()
                )
        ));
    }
}
