package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.app.port.MedicationRepository;
import com.musala.dev.drones.application.domain.exception.notfound.DroneNotFoundException;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.MedicationMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.DroneJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MedicationRepositoryImpl implements MedicationRepository {

    private final DroneJpaRepository droneJpaRepository;
    private final MedicationMapper medicationMapper;

    @Override
    @Transactional
    public List<Medication> loadMedications(String serialNumber, List<Medication> medications) {
        var droneEntity = droneJpaRepository.findById(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber));
        var medicationEntities = medicationMapper.toMedicationEntities(medications);
        droneEntity.addMedications(medicationEntities);
        droneEntity.setState(State.LOADED);
        var updated = droneJpaRepository.save(droneEntity);
        return medicationMapper.toMedications(updated.getMedications());
    }
}
