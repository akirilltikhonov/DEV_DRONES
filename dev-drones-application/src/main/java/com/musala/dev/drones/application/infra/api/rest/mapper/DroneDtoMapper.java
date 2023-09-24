package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.DroneDto;
import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.application.domain.model.Drone;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , uses = {
        MedicationDtoMapper.class
}
)
public interface DroneDtoMapper {

    @Mapping(target = "state", constant = "IDLE")
    @Mapping(target = "medications", ignore = true)
    Drone toDrone(RegisterDroneDto registerDroneDto);

    DroneDto toDroneDto(Drone drone);
}