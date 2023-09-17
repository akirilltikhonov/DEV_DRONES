package com.musala.dev.drones.domain.model;

import com.musala.dev.drones.domain.model.enums.Model;
import com.musala.dev.drones.domain.model.enums.State;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Drone {

    String serialNumber;
    Model model;
    Integer weightLimit;
    Integer batteryCapacity;
    State state;
}
