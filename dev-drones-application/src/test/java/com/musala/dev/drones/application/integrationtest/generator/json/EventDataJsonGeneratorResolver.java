package com.musala.dev.drones.application.integrationtest.generator.json;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.integrationtest.generator.json.impl.CheckBatteryJsonGenerator;

import java.util.HashSet;
import java.util.Set;

public class EventDataJsonGeneratorResolver {

    private final Set<EventDataJsonGenerator> eventDataJsonGenerators = new HashSet<>(Set.of(
            new CheckBatteryJsonGenerator()
    ));

    public EventDataJsonGenerator resolve(EventType eventType) {
        return eventDataJsonGenerators.stream()
                .filter(generator -> generator.isCompatible(eventType))
                .reduce((a, b) -> {
                    throw new RuntimeException("Find more than one generators for " + eventType.name());
                })
                .orElseThrow(() -> new RuntimeException("Generator for has not been found for " + eventType.name()));
    }
}
