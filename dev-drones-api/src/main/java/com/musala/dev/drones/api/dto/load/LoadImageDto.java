package com.musala.dev.drones.api.dto.load;

import lombok.Builder;

@Builder(toBuilder = true)
public record LoadImageDto(
        String imageBase64
) {
}
