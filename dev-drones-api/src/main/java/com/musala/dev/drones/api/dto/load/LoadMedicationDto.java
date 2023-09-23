package com.musala.dev.drones.api.dto.load;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record LoadMedicationDto(
        @NotNull
        String name,
        @NotNull
        @Min(1)
        @Max(500)
        Integer weight,
        @NotNull
        String code
) {
}
