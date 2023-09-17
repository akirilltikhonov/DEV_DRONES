package com.musala.dev.drones.application.infra.api.rest.controller;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneRequestMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneResponseMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationRequestMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationResponseMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @InjectMocks
    private DroneController droneController;
    @Mock
    private DroneRequestMapper droneRequestMapper;
    @Mock
    private DroneResponseMapper droneResponseMapper;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRequestMapper medicationRequestMapper;
    @Mock
    private MedicationResponseMapper medicationResponseMapper;
    @Mock
    private MedicationService medicationService;

    private final EasyRandom random = new EasyRandom();

    @Test
    void register() {
        var registerDroneDto = random.nextObject(RegisterDroneDto.class);

        var droneToRegister = random.nextObject(Drone.class);
        doReturn(droneToRegister).when(droneRequestMapper).toDrone(registerDroneDto);
        var registeredDrone = random.nextObject(Drone.class);
        doReturn(registeredDrone).when(droneRepository).register(droneToRegister);
        var droneDto = random.nextObject(DroneDto.class);
        doReturn(droneDto).when(droneResponseMapper).toDroneDto(registeredDrone);

        assertThat(droneController.register(registerDroneDto)).isEqualTo(ResponseEntity.ok(droneDto));
    }

    @Test
    void loadMedications() {
        String serialNumber = UUID.randomUUID().toString();
        var medicationsRequestDto = List.of(random.nextObject(LoadMedicationRequestDto.class));

        var medicationsToLoad = List.of(random.nextObject(Medication.class));
        doReturn(medicationsToLoad).when(medicationRequestMapper).toMedications(medicationsRequestDto);
        var loadedMedications = List.of(random.nextObject(Medication.class));
        doReturn(loadedMedications).when(medicationService).loadMedications(serialNumber, medicationsToLoad);
        var loadedMedicationsDto = List.of(random.nextObject(MedicationDto.class));
        doReturn(loadedMedicationsDto).when(medicationResponseMapper).toMedications(loadedMedications);

        assertThat(droneController.loadMedications(serialNumber, medicationsRequestDto)).isEqualTo(ResponseEntity.ok(loadedMedicationsDto));
    }
}