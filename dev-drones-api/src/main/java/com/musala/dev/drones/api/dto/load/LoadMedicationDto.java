package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder(toBuilder = true)
public class LoadMedicationDto {
        @NotNull
        @Pattern(regexp = "^[\\w_-]+$")
        String name;
        @NotNull
        @Min(0)
        @Max(500)
        Integer weight;
        @NotNull
        @Pattern(regexp = "^[\\d_A-Z]+$")
        String code;
        @Valid
        LoadImageDto image;
}
