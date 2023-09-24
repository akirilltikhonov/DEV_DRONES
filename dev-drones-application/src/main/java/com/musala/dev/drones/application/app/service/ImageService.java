package com.musala.dev.drones.application.app.service;

import com.musala.dev.drones.application.domain.model.Medication;

import java.util.List;

public interface ImageService {
    List<Medication> saveImages(List<Medication> medications);
}
