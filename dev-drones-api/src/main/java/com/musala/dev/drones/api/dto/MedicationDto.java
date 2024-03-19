package com.musala.dev.drones.api.dto;

import lombok.Builder;

@Builder
public class MedicationDto {
    Long medicationId;
    String name;
    Integer weight;
    String code;
    ImageDto image;
}
