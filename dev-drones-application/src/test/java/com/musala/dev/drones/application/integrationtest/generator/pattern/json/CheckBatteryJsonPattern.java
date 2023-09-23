package com.musala.dev.drones.application.integrationtest.generator.pattern.json;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CheckBatteryJsonPattern implements EventDataJsonPattern {

    EventType eventType = EventType.CHECK_BATTERY;

    String serialNumber;
    Integer batteryLevel;
}
