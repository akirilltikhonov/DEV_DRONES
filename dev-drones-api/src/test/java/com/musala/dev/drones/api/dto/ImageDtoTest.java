package com.musala.dev.drones.api.dto;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class ImageDtoTest {

    @Test
    void builder() {
        var image = ImageDto.builder()
            .imageId(UUID.randomUUID())
            .s3Url("s3url")
            .build();
    }
}