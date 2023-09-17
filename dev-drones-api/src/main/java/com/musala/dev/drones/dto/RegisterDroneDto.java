package com.musala.dev.drones.dto;

import com.musala.dev.drones.dto.enums.Model;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder(toBuilder = true)
public record RegisterDroneDto(
        @NotNull
        @Size(max = 100)
        String serialNumber,
        @NotNull
        Model model,
        @NotNull
        @Min(0)
        @Max(100)
        Integer batteryCapacity,
        @NotNull
        @Min(0)
        @Max(500)
        Integer weightLimit
) {
}
