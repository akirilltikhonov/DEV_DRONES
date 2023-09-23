package com.musala.dev.drones.application.infra.persistence.jpa.repository;

import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.custom.DroneRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneJpaRepository extends JpaRepository<DroneEntity, String>, DroneRepositoryCustom {

    Optional<DroneEntity> findBySerialNumberAndState(String serialNumber, State state);

    List<DroneEntity> findByBatteryLevelIsLessThanEqual(Integer batteryLevel, Pageable pageable);
}
