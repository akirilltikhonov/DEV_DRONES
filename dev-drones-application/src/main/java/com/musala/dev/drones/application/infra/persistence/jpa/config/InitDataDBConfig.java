package com.musala.dev.drones.application.infra.persistence.jpa.config;

import com.musala.dev.drones.application.domain.model.enums.Model;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class InitDataDBConfig {

    private final DroneJpaRepository droneJpaRepository;

    @PostConstruct
    void init() {
        for (int i = 0; i < 2; i++) {
            var droneEntity = new DroneEntity();
            droneEntity.setSerialNumber(UUID.randomUUID().toString());
            droneEntity.setModel(Model.HEAVYWEIGHT);
            droneEntity.setWeightLimit(500);
            droneEntity.setBatteryLevel(40);
            droneEntity.setState(State.IDLE);
            droneJpaRepository.save(droneEntity);
        }
        for (int i = 0; i < 2; i++) {
            var droneEntity = new DroneEntity();
            droneEntity.setSerialNumber(UUID.randomUUID().toString());
            droneEntity.setModel(Model.LIGHTWEIGHT);
            droneEntity.setWeightLimit(500);
            droneEntity.setBatteryLevel(90);
            droneEntity.setState(State.IDLE);
            droneJpaRepository.save(droneEntity);
        }
        for (int i = 0; i < 3; i++) {
            var droneEntity = new DroneEntity();
            droneEntity.setSerialNumber(UUID.randomUUID().toString());
            droneEntity.setModel(Model.LIGHTWEIGHT);
            droneEntity.setWeightLimit(500);
            droneEntity.setBatteryLevel(90);
            droneEntity.setState(State.DELIVERING);
            var medicationEntity = new MedicationEntity();
            medicationEntity.setName("name" + i);
            medicationEntity.setCode("code" + i);
            medicationEntity.setWeight(100);
            droneEntity.addMedication(medicationEntity);
            droneJpaRepository.save(droneEntity);
        }
    }
}
