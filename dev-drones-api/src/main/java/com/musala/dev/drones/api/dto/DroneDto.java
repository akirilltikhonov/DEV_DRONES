package com.musala.dev.drones.api.dto;

import com.musala.dev.drones.api.dto.enums.Model;
import com.musala.dev.drones.api.dto.enums.State;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
public class DroneDto {
    String serialNumber;
    Model model;
    Integer weightLimit;
    Integer batteryLevel;
    State state;
    List<MedicationDto> medications;
}
