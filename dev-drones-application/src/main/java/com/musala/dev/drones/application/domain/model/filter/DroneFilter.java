package com.musala.dev.drones.application.domain.model.filter;

import com.musala.dev.drones.application.domain.model.enums.State;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class DroneFilter {
        Integer minBatteryLevel;
        List<State> states;
}