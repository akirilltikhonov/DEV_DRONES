package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , uses = MedicationMapper.class
)
@DecoratedWith(DroneDecoratorMapper.class)
public interface DroneMapper {

    DroneEntity toDroneEntity(Drone drone);

    Drone toDrone(DroneEntity drone);

    List<Drone> toDrones(List<DroneEntity> drones);
}
