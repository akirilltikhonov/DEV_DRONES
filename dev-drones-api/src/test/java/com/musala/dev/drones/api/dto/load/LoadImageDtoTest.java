package com.musala.dev.drones.api.dto.load;

import org.junit.jupiter.api.Test;

class LoadImageDtoTest {

    @Test
    void builder() {
        var loadImageDto = LoadImageDto.builder()
            .imageBase64("imageBase64")
            .build();
    }
}