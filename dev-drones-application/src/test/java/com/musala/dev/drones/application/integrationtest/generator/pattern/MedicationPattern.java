package com.musala.dev.drones.application.integrationtest.generator.pattern;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class MedicationPattern {

    String name;
    Integer weight;
    String code;
}
