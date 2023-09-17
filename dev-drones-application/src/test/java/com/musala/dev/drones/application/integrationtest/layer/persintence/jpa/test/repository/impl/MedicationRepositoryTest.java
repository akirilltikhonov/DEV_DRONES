package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.port.MedicationRepository;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.MedicationMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.MedicationEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MedicationRepositoryTest extends JpaTest {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DroneJpaRepository droneJpaRepository;
    @Autowired
    private MedicationMapper medicationMapper;

    @Test
    void loadMedications() {
        var medicationEntity1 = MedicationEntityGenerator.next();
        var medicationEntity2 = MedicationEntityGenerator.next();
        var droneEntity = DroneEntityGenerator.next(DronePattern.builder()
                .state(State.IDLE)
                .build());
        droneEntity.addMedication(medicationEntity1);
        droneEntity.addMedication(medicationEntity2);
        droneEntity = droneJpaRepository.save(droneEntity);
        String serialNumber = droneEntity.getSerialNumber();

        var saved = droneRepository.findBySerialNumber(serialNumber);
        assertThat(saved.getMedications().size()).isEqualTo(2);
        assertThat(saved.getState()).isEqualTo(State.IDLE);

        var medication3 = medicationMapper.toMedication(MedicationEntityGenerator.next());
        var medication4 = medicationMapper.toMedication(MedicationEntityGenerator.next());
        var medications = medicationRepository.loadMedications(serialNumber, List.of(medication3, medication4));
        medications.forEach(m -> assertThat(m.getMedicationId()).isNotNull());

        var afterLoad = droneRepository.findBySerialNumber(serialNumber);
        assertThat(afterLoad.getMedications().size()).isEqualTo(4);
        assertThat(afterLoad.getState()).isEqualTo(State.LOADED);
    }
}
