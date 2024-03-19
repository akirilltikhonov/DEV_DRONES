package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder(toBuilder = true)
public class LoadImageDto {
        @NotNull
        String imageBase64;
}
