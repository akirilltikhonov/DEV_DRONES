package com.musala.dev.drones.application.domain.service;

import com.musala.dev.drones.application.domain.exception.badrequest.LoadMedicationException;
import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Medication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicationLoadValidation {

    public void checkTotalWeight(Drone drone, List<Medication> medications) {
        Integer loaded = drone.totalWeight();
        Integer toLoad = MedicationsWeightCounter.count(medications);
        if (loaded + toLoad > 500) {
            throw new LoadMedicationException(drone.getSerialNumber(), loaded);
        }
    }
}
