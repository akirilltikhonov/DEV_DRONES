package com.musala.dev.drones.application.domain.service;

import com.musala.dev.drones.application.domain.exception.badrequest.LoadMedicationException;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class MedicationLoadValidationTest {

    @InjectMocks
    private MedicationLoadValidation medicationLoadValidation;

    @Test
    void checkTotalWeight() {
        var drone = Drone.builder()
                .medications(List.of(Medication.builder()
                        .weight(250)
                        .build()))
                .build();
        var medications = List.of(Medication.builder()
                .weight(250)
                .build());
        medicationLoadValidation.checkTotalWeight(drone, medications);
    }

    @Test
    void checkTotalWeightException() {
        var drone = Drone.builder()
                .serialNumber("serialNumber")
                .medications(List.of(Medication.builder()
                        .weight(250)
                        .build()))
                .build();
        var medications = List.of(Medication.builder()
                .weight(300)
                .build());
        assertThatThrownBy(() -> medicationLoadValidation.checkTotalWeight(drone, medications))
                .isInstanceOf(LoadMedicationException.class)
                .hasMessage(new LoadMedicationException(drone.getSerialNumber(), 250).getMessage());
    }
}