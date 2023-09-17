package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.DroneRepository;
import com.musala.dev.drones.application.app.port.MedicationRepository;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.service.MedicationLoadValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final DroneRepository droneRepository;
    private final MedicationLoadValidation medicationLoadValidation;
    private final MedicationRepository medicationRepository;

    @Override
    public List<Medication> loadMedications(String serialNumber, List<Medication> medications) {
        var drone = droneRepository.findBySerialNumber(serialNumber);
        medicationLoadValidation.checkTotalWeight(drone, medications);
        return medicationRepository.loadMedications(serialNumber, medications);
    }
}
