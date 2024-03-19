package com.musala.dev.drones.api.dto.load;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
public class LoadMedicationRequestDto {
        @NotEmpty
        List<@Valid LoadMedicationDto> medications;
}
