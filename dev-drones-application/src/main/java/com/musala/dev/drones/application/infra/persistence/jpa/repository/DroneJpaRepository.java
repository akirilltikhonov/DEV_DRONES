package com.musala.dev.drones.application.infra.persistence.jpa.repository;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.custom.DroneRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, String>, DroneRepositoryCustom {
}
