package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
public record LoadMedicationRequestDto(
        @NotEmpty
        List<@Valid LoadMedicationDto> medications
) {
}
