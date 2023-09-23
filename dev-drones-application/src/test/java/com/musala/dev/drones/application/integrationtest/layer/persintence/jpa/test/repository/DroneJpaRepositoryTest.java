package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository;

import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DroneJpaRepositoryTest extends JpaTest {

    @Autowired
    private DroneJpaRepository droneJpaRepository;

    @Test
    void saveIncorrectLoadingState() {
        assertThatThrownBy(() -> droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                .batteryLevel(24)
                .state(State.LOADING)
                .build())))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void saveCorrectLoadingState() {
        assertThatCode(() -> droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                .batteryLevel(25)
                .state(State.LOADING)
                .build())))
                .doesNotThrowAnyException();
    }

    @Test
    void saveCorrectIdleState() {
        assertThatCode(() -> droneJpaRepository.save(DroneEntityGenerator.next(DronePattern.builder()
                .batteryLevel(24)
                .state(State.IDLE)
                .build())))
                .doesNotThrowAnyException();
    }
}