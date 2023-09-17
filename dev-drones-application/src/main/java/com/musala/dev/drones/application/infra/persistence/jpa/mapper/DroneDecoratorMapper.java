package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
public abstract class DroneDecoratorMapper implements DroneMapper {

    @Autowired
    private DroneMapper droneMapper;

    @Override
    public DroneEntity toDroneEntity(Drone drone) {
        var droneEntity = droneMapper.toDroneEntity(drone);
        if (droneEntity != null) {
            droneEntity.addMedications(droneEntity.getMedications());
        }
        return droneEntity;
    }
}