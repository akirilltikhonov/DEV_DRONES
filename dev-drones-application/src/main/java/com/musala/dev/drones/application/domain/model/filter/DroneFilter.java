package com.musala.dev.drones.application.domain.model.filter;

import com.musala.dev.drones.application.domain.model.enums.State;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record DroneFilter(
        Integer minBatteryLevel,
        List<State> states
) {
}