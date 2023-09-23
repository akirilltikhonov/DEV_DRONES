package com.musala.dev.drones.application.integrationtest.generator.json.impl;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.EventDataJson;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.impl.CheckBatteryJson;
import com.musala.dev.drones.application.integrationtest.generator.json.EventDataJsonGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.json.CheckBatteryJsonPattern;
import com.musala.dev.drones.application.integrationtest.generator.pattern.json.EventDataJsonPattern;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import static org.jeasy.random.FieldPredicates.named;

public class CheckBatteryJsonGenerator implements EventDataJsonGenerator {

    private final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
                    .randomize(named("eventType"), () -> EventType.CHECK_BATTERY)
                    .stringLengthRange(10, 20)
    );

    @Override
    public EventDataJson next(EventDataJsonPattern pattern) {
        var json = random.nextObject(CheckBatteryJson.class);
        var castedPattern = (CheckBatteryJsonPattern) pattern;
        if (castedPattern != null) {
            if (castedPattern.getSerialNumber() != null) {
                json = json.toBuilder().serialNumber(castedPattern.getSerialNumber()).build();
            }
            if (castedPattern.getBatteryLevel() != null) {
                json = json.toBuilder().batteryLevel(castedPattern.getBatteryLevel()).build();
            }
        }
        return json;
    }

    @Override
    public boolean isCompatible(EventType eventType) {
        return EventType.CHECK_BATTERY == eventType;
    }
}
