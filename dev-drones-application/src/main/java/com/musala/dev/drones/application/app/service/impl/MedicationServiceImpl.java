package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.service.MedicationService;
import com.musala.dev.drones.application.domain.model.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {

    @Override
    public List<Medication> loadMedications(String serialNumber, List<Medication> medications) {
        return null;
    }
}
