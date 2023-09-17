package com.musala.dev.drones.integrationtest.layer.persintence.jpa.test.repository.impl;

import com.musala.dev.drones.app.port.DroneRepository;
import com.musala.dev.drones.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.integrationtest.layer.persintence.jpa.JpaTest;
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
        assertThat(registered)
                .isEqualTo(found);
    }
}
