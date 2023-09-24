package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.repository.DroneRepository;
import com.musala.dev.drones.application.app.port.repository.MedicationRepository;
import com.musala.dev.drones.application.app.service.ImageService;
import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.domain.service.MedicationLoadValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    private final DroneRepository droneRepository;
    private final MedicationLoadValidation medicationLoadValidation;
    private final ImageService imageService;
    private final MedicationRepository medicationRepository;

    @Override
    @Transactional
    public List<Medication> loadMedications(String serialNumber, List<Medication> medications) {
        var drone = droneRepository.findToLoadMedication(serialNumber);
        medicationLoadValidation.checkTotalWeight(drone, medications);
        var medicationsWithSavedImages = imageService.saveImages(medications);
        return medicationRepository.loadMedications(serialNumber, medicationsWithSavedImages);
    }
}
