package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DroneRequestMapper {

    @Mapping(target = "state", constant = "IDLE")
    @Mapping(target = "medications", ignore = true)
    Drone toDrone(RegisterDroneDto registerDroneDto);
}