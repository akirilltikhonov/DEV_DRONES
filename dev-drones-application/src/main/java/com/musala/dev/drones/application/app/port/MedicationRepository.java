package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public interface MedicationRepository {

    List<Medication> loadMedications(String serialNumber, List<Medication> medications);
}
