package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class DroneRepositoryTest extends JpaTest {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneMapper droneMapper;

    @Test
    void register() {
        var registered = droneRepository.register(droneMapper.toDrone(DroneEntityGenerator.next()));
        var found = droneRepository.findBySerialNumber(registered.getSerialNumber());
        Assertions.assertThat(registered)
                .isEqualTo(found);
    }
}
