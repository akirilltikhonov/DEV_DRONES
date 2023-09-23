package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.domain.exception.notfound.DroneNotFoundException;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.model.filter.DroneFilter;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.DroneMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.DroneEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class DroneRepositoryImplTest {

    @InjectMocks
    private DroneRepositoryImpl droneRepository;
    @Mock
    private DroneJpaRepository droneJpaRepository;
    @Mock
    private DroneMapper droneMapper;

    private final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
    );

    @Test
    void register() {
        var drone = random.nextObject(Drone.class);

        var droneEntityToSave = random.nextObject(DroneEntity.class);
        doReturn(droneEntityToSave).when(droneMapper).toDroneEntity(drone);
        var savedDroneEntity = random.nextObject(DroneEntity.class);
        doReturn(savedDroneEntity).when(droneJpaRepository).save(droneEntityToSave);
        var savedDrone = Drone.builder()
                .build();
        doReturn(savedDrone).when(droneMapper).toDrone(savedDroneEntity);
        assertThat(droneRepository.register(drone)).isEqualTo(savedDrone);
    }

    @Test
    void findBySerialNumber() {
        String serialNumber = "1L";
        var droneEntity = DroneEntityGenerator.next();
        doReturn(Optional.of(droneEntity)).when(droneJpaRepository).findById(serialNumber);
        var drone = Drone.builder().serialNumber(serialNumber).build();
        doReturn(drone).when(droneMapper).toDrone(droneEntity);
        assertThat(droneRepository.findBySerialNumber(serialNumber)).isEqualTo(drone);
    }

    @Test
    void findBySerialNumberNotFound() {
        String serialNumber = "1L";
        assertThatThrownBy(() -> droneRepository.findBySerialNumber(serialNumber))
                .isInstanceOf(DroneNotFoundException.class)
                .hasMessage(new DroneNotFoundException(serialNumber).getMessage());
    }

    @Test
    void findAvailableDronesForLoading() {
        var filter = DroneFilter.builder()
                .minBatteryLevel(25)
                .states(List.of(State.IDLE))
                .build();

        String serialNumber = "1L";
        var droneEntities = List.of(DroneEntityGenerator.next(DronePattern.builder()
                .serialNumber(serialNumber)
                .build()));
        doReturn(droneEntities).when(droneJpaRepository).findByFilter(filter);
        assertThat(droneRepository.findAvailableDronesForLoading()).isEqualTo(List.of(serialNumber));
    }
}