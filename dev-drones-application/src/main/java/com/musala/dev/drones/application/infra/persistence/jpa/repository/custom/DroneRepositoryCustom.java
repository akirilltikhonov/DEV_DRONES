package com.musala.dev.drones.application.infra.persistence.jpa.repository.custom;

import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;

import java.util.List;

public interface DroneRepositoryCustom {

    List<DroneEntity> findByFilter(DroneFilter filter);
}
