package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.port.MedicationRepository;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.service.MedicationLoadValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {

    @InjectMocks
    private MedicationServiceImpl medicationService;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationLoadValidation medicationLoadValidation;
    @Mock
    private MedicationRepository medicationRepository;

    @Test
    void loadMedications() {
        String serialNumber = "serialNumber";
        List<Medication> medications = List.of(Medication.builder().build());

        var drone = Drone.builder().serialNumber(UUID.randomUUID().toString()).build();
        doReturn(drone).when(droneRepository).findBySerialNumber(serialNumber);

        var loadedMedications = List.of(Medication.builder().medicationId(1L).build());
        doReturn(loadedMedications).when(medicationRepository).loadMedications(serialNumber, medications);

        assertThat(medicationService.loadMedications(serialNumber, medications))
                .isEqualTo(loadedMedications);

        verify(medicationLoadValidation).checkTotalWeight(drone, medications);
    }
}