package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , unmappedSourcePolicy = ReportingPolicy.ERROR
)
public interface DroneMapper {

    DroneEntity toDroneEntity(Drone drone);

    Drone toDrone(DroneEntity drone);
}
