package com.musala.dev.drones.application.app.service.impl;

import com.musala.dev.drones.application.app.port.adapter.S3Adapter;
import com.musala.dev.drones.application.app.service.ImageService;
import com.musala.dev.drones.application.domain.model.Medication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final S3Adapter s3Adapter;

    @Override
    public List<Medication> saveImages(List<Medication> medications) {
        return medications.stream()
                .map(m -> m.toBuilder()
                        .image(s3Adapter.storeImage(m.getImage().getImageBase64()))
                        .build())
                .toList();
    }
}
