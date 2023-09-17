package com.musala.dev.drones.application.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Medication {

    Long medicationId;
    String name;
    Integer weight;
    String code;
}
