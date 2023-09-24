package com.musala.dev.drones.application.infra.api.rest.controller;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneService;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneDtoMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationDtoMapper;
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
    private DroneDtoMapper droneDtoMapper;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationDtoMapper medicationDtoMapper;
    @Mock
    private MedicationService medicationService;
    @Mock
    private DroneService droneService;

    private final EasyRandom random = new EasyRandom();

    @Test
    void register() {
        var registerDroneDto = random.nextObject(RegisterDroneDto.class);

        var droneToRegister = random.nextObject(Drone.class);
        doReturn(droneToRegister).when(droneDtoMapper).toDrone(registerDroneDto);
        var registeredDrone = random.nextObject(Drone.class);
        doReturn(registeredDrone).when(droneRepository).register(droneToRegister);
        var droneDto = random.nextObject(DroneDto.class);
        doReturn(droneDto).when(droneDtoMapper).toDroneDto(registeredDrone);

        assertThat(droneController.register(registerDroneDto)).isEqualTo(ResponseEntity.ok(droneDto));
    }

    @Test
    void loadMedications() {
        String serialNumber = UUID.randomUUID().toString();
        var requestDto = LoadMedicationRequestDto.builder()
                .medications(List.of(random.nextObject(LoadMedicationDto.class)))
                .build();

        var medicationsToLoad = List.of(random.nextObject(Medication.class));
        doReturn(medicationsToLoad).when(medicationDtoMapper).toMedications(requestDto.medications());
        var loadedMedications = List.of(random.nextObject(Medication.class));
        doReturn(loadedMedications).when(medicationService).loadMedications(serialNumber, medicationsToLoad);
        var loadedMedicationsDto = List.of(random.nextObject(MedicationDto.class));
        doReturn(loadedMedicationsDto).when(medicationDtoMapper).toMedicationDtos(loadedMedications);

        assertThat(droneController.loadMedications(serialNumber, requestDto)).isEqualTo(ResponseEntity.ok(loadedMedicationsDto));
    }

    @Test
    void getLoadedMedications() {
        String serialNumber = UUID.randomUUID().toString();

        var loadedMedications = List.of(random.nextObject(Medication.class));
        doReturn(loadedMedications).when(droneService).getLoadedMedications(serialNumber);
        var loadedMedicationsDto = List.of(random.nextObject(MedicationDto.class));
        doReturn(loadedMedicationsDto).when(medicationDtoMapper).toMedicationDtos(loadedMedications);

        assertThat(droneController.getLoadedMedications(serialNumber)).isEqualTo(ResponseEntity.ok(loadedMedicationsDto));
    }

    @Test
    void getAvailableDronesForLoading() {
        var drones = List.of(Drone.builder().serialNumber(UUID.randomUUID().toString()).build());
        doReturn(drones).when(droneService).findAvailableDronesForLoading();
        assertThat(droneController.getAvailableDronesForLoading())
                .isEqualTo(ResponseEntity.ok(drones));
    }

    @Test
    void getBatteryLevel() {
        String serialNumber = UUID.randomUUID().toString();

        Integer batteryLevel = 50;
        doReturn(batteryLevel).when(droneService).getBatteryLevel(serialNumber);

        assertThat(droneController.getBatteryLevel(serialNumber)).isEqualTo(ResponseEntity.ok(batteryLevel));
    }
}