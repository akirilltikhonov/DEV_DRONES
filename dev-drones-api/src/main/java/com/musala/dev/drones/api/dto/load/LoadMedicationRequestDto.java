package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

@Builder
public record LoadMedicationRequestDto(
        String name,
        Integer weight,
        String code
) {
}
