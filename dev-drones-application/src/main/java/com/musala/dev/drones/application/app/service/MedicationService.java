package com.musala.dev.drones.application.app.service;

import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public interface MedicationService {
    List<Medication> loadMedications(String serialNumber, List<Medication> medications);
}
