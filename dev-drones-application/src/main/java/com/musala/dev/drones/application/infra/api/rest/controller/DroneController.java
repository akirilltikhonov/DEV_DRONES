package com.musala.dev.drones.application.infra.api.rest.controller;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.DroneService;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneDtoMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationDtoMapper;
import com.musala.dev.drones.client.DroneControllerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev-drones")
public class DroneController implements DroneControllerApi {

    private final DroneDtoMapper droneDtoMapper;
    private final DroneRepository droneRepository;
    private final MedicationDtoMapper medicationDtoMapper;
    private final MedicationService medicationService;
    private final DroneService droneService;

    @PostMapping(value = "/register")
    public ResponseEntity<DroneDto> register(@RequestBody @Valid RegisterDroneDto registerDroneDto) {
        var droneToRegister = droneDtoMapper.toDrone(registerDroneDto);
        var registeredDrone = droneRepository.register(droneToRegister);
        return ResponseEntity.ok(droneDtoMapper.toDroneDto(registeredDrone));
    }

    @PutMapping(value = "/{serialNumber}/medications")
    public ResponseEntity<List<MedicationDto>> loadMedications(
            @PathVariable @NotNull String serialNumber,
            @RequestBody @NotNull @Valid LoadMedicationRequestDto requestDto
    ) {
        var medicationsToLoad = medicationDtoMapper.toMedications(requestDto.medications());
        var loadedMedications = medicationService.loadMedications(serialNumber, medicationsToLoad);
        return ResponseEntity.ok(medicationDtoMapper.toMedicationDtos(loadedMedications));
    }

    @GetMapping(value = "/{serialNumber}/medications")
    public ResponseEntity<List<MedicationDto>> getLoadedMedications(@PathVariable @NotNull String serialNumber) {
        var loadedMedications = droneService.getLoadedMedications(serialNumber);
        return ResponseEntity.ok(medicationDtoMapper.toMedicationDtos(loadedMedications));
    }

    @GetMapping(value = "/available-drones")
    public ResponseEntity<List<Drone>> getAvailableDronesForLoading() {
        var availableDrones = droneService.findAvailableDronesForLoading();
        return ResponseEntity.ok(availableDrones);
    }

    @GetMapping(value = "/{serialNumber}/battery")
    public ResponseEntity<Integer> getBatteryLevel(@PathVariable @NotNull String serialNumber) {
        Integer batteryLevel = droneService.getBatteryLevel(serialNumber);
        return ResponseEntity.ok(batteryLevel);
    }
}
