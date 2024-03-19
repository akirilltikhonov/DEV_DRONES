package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.adapter.S3Adapter;
import com.musala.dev.drones.application.domain.model.Image;
import com.musala.dev.drones.application.domain.model.Medication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @InjectMocks
    private ImageServiceImpl imageService;
    @Mock
    private S3Adapter s3Adapter;

    @Test
    void saveImages() {
        String imageBase64 = "imageBase64";
        var image = Image.builder()
                .imageBase64(imageBase64)
                .build();
        var medications = List.of(Medication.builder()
                .image(image)
                .build());

        var savedImage = image.toBuilder()
                .imageId(UUID.randomUUID())
                .s3Url("s3Url")
                .build();
        doReturn(savedImage).when(s3Adapter).storeImage(imageBase64);

        assertThat(imageService.saveImages(medications))
                .isEqualTo(medications.stream()
                        .map(m -> m.toBuilder()
                                .image(savedImage)
                                .build())
                        .collect(Collectors.toList()));
    }
}