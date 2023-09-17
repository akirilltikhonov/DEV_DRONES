package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.MedicationMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MedicationRepositoryImplTest {

    @InjectMocks
    private MedicationRepositoryImpl medicationRepository;
    @Mock
    private DroneJpaRepository droneJpaRepository;
    @Mock
    private MedicationMapper medicationMapper;

    private final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
    );

    @Test
    void loadMedications() {
        String serialNumber = "serialNumber";
        var medications = List.of(random.nextObject(Medication.class));

        var droneEntity = random.nextObject(DroneEntity.class);
        doReturn(Optional.of(droneEntity)).when(droneJpaRepository).findById(serialNumber);

        var medicationEntities = List.of(random.nextObject(MedicationEntity.class));
        doReturn(medicationEntities).when(medicationMapper).toMedicationEntities(medications);
        droneEntity.addMedications(medicationEntities);
        droneEntity.setState(State.LOADED);

        var updated = random.nextObject(DroneEntity.class);
        doReturn(updated).when(droneJpaRepository).save(droneEntity);

        var updatedMedications = List.of(random.nextObject(Medication.class));
        doReturn(updatedMedications).when(medicationMapper).toMedications(updated.getMedications());

        assertThat(medicationRepository.loadMedications(serialNumber, medications))
                .isEqualTo(updatedMedications);
    }
}