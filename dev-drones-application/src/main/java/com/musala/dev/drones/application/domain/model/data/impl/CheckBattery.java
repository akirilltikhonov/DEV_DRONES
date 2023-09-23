package com.musala.dev.drones.application.domain.model.data.impl;

import com.musala.dev.drones.application.domain.model.data.EventData;
import com.musala.dev.drones.application.domain.model.enums.EventType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CheckBattery implements EventData {

    EventType eventType = EventType.CHECK_BATTERY;

    String serialNumber;
    Integer batteryLevel;
}
