package com.musala.dev.drones.infra.api.rest.mapper;

import com.musala.dev.drones.domain.model.Drone;
import com.musala.dev.drones.dto.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface DroneResponseMapper {

    DroneDto toDroneDto(Drone drone);
}