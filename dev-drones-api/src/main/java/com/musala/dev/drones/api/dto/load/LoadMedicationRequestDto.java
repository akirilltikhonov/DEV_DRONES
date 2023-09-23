package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

import java.util.List;

@Builder
public record LoadMedicationRequestDto(
        List<LoadMedicationDto> medications
) {
}
