package com.musala.dev.drones.application.infra.api.rest.controller;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.client.DroneControllerApi;
import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneRequestMapper;
import com.musala.dev.drones.application.infra.api.rest.mapper.DroneResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/dev-drones")
public class DroneController implements DroneControllerApi {

    private final DroneRequestMapper droneRequestMapper;
    private final DroneResponseMapper droneResponseMapper;
    private final DroneRepository droneRepository;

    @PostMapping(value = "/register")
    public ResponseEntity<DroneDto> register(@RequestBody @Valid RegisterDroneDto registerDroneDto) {
        var droneToRegister = droneRequestMapper.toDrone(registerDroneDto);
        var registeredDrone = droneRepository.register(droneToRegister);
        return ResponseEntity.ok(droneResponseMapper.toDroneDto(registeredDrone));
    }
}
