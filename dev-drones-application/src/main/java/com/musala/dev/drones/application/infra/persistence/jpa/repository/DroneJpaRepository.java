package com.musala.dev.drones.application.infra.persistence.jpa.repository;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.custom.DroneRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, String>, DroneRepositoryCustom {

    List<DroneEntity> findByBatteryLevelIsLessThanEqual(Integer batteryLevel, Pageable pageable);
}
