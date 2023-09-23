package com.musala.dev.drones.application.integrationtest.generator.pattern;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.integrationtest.generator.pattern.json.CheckBatteryJsonPattern;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class EventPattern {

    EventType eventType;
    LocalDateTime eventTime;
    CheckBatteryJsonPattern dataPattern;
}
