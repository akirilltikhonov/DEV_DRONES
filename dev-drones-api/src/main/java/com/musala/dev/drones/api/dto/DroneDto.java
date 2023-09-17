package com.musala.dev.drones.api.dto;

import com.musala.dev.drones.api.dto.enums.Model;
import com.musala.dev.drones.api.dto.enums.State;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record DroneDto(
        String serialNumber,
        Model model,
        Integer weightLimit,
        Integer batteryCapacity,
        State state,
        List<MedicationDto> medications
) {
}
