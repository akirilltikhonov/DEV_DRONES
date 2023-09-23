package com.musala.dev.drones.application.domain.model;

import com.musala.dev.drones.application.domain.model.enums.Model;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.musala.dev.drones.application.domain.service.MedicationsWeightCounter;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class Drone {

    String serialNumber;
    Model model;
    Integer weightLimit;
    Integer batteryLevel;
    State state;
    @Builder.Default
    List<Medication> medications = new ArrayList<>();

    public Integer totalWeight() {
        return MedicationsWeightCounter.count(medications);
    }
}
