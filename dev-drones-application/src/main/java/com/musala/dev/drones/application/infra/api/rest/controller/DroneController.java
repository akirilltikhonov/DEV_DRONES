package com.musala.dev.drones.application.infra.api.rest.controller;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneRequestMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneResponseMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationRequestMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.MedicationResponseMapper;
import com.musala.dev.drones.client.DroneControllerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    private final DroneRequestMapper droneRequestMapper;
    private final DroneResponseMapper droneResponseMapper;
    private final DroneRepository droneRepository;
    private final MedicationRequestMapper medicationRequestMapper;
    private final MedicationResponseMapper medicationResponseMapper;
    private final MedicationService medicationService;

    @PostMapping(value = "/register")
    public ResponseEntity<DroneDto> register(@RequestBody @Valid RegisterDroneDto registerDroneDto) {
        var droneToRegister = droneRequestMapper.toDrone(registerDroneDto);
        var registeredDrone = droneRepository.register(droneToRegister);
        return ResponseEntity.ok(droneResponseMapper.toDroneDto(registeredDrone));
    }

    @PutMapping(value = "/{serialNumber}")
    public ResponseEntity<List<MedicationDto>> loadMedications(
            @PathVariable @NotNull String serialNumber,
            @RequestBody List<@Valid LoadMedicationRequestDto> requestDtos
    ) {
        var medicationsToLoad = medicationRequestMapper.toMedications(requestDtos);
        var loadedMedications = medicationService.loadMedications(serialNumber, medicationsToLoad);
        return ResponseEntity.ok(medicationResponseMapper.toMedications(loadedMedications));
    }
}
