package com.musala.dev.drones.application.integrationtest.generator.test;

import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.domain.model.enums.Model;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DroneEntityGeneratorTest {

    private void assertNextDroneEntity(DroneEntity drone) {
        assertThat(drone).isNotNull();
        assertThat(drone).hasNoNullFieldsOrPropertiesExcept();
        assertThat(drone).hasAllNullFieldsOrPropertiesExcept(
                "serialNumber"
                , "model"
                , "weightLimit"
                , "batteryLevel"
                , "state"
                , "medications"
        );
        assertThat(drone.getMedications()).isEmpty();
    }

    @Test
    void next() {
        assertNextDroneEntity(DroneEntityGenerator.next());
    }

    @Test
    void nextWithNullPattern() {
        assertNextDroneEntity(DroneEntityGenerator.next(null));
    }

    @Test
    void nextWithPattern() {
        final var pattern = DronePattern.builder()
                .serialNumber("serialNumber")
                .model(Model.LIGHTWEIGHT)
                .weightLimit(500)
                .batteryLevel(100)
                .state(State.IDLE)
                .build();
        final var drone = DroneEntityGenerator.next(pattern);
        assertThat(pattern)
                .usingRecursiveComparison()
                .isEqualTo(drone);
    }

    @Test
    void nextWithPatternAssertionError() {
        final DronePattern pattern = DronePattern.builder().build();
        final DroneEntity drone = DroneEntityGenerator.next(pattern);
        assertThatThrownBy(() -> assertThat(pattern).usingRecursiveComparison().isEqualTo(drone))
                .isInstanceOf(AssertionError.class);
    }
}