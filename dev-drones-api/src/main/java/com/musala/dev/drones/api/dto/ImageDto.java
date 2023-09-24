package com.musala.dev.drones.api.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ImageDto(
        UUID imageId,
        String s3Url
) {
}
