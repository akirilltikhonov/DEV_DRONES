package com.musala.dev.drones.application.domain.service;

import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public class MedicationsWeightCounter {

    public static Integer count(List<Medication> medications) {
        return medications.stream()
                .map(Medication::getWeight)
                .reduce(0, Integer::sum);
    }
}