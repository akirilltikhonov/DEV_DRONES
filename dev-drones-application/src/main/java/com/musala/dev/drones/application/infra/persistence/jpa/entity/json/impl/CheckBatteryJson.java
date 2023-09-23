package com.musala.dev.drones.application.infra.persistence.jpa.entity.json.impl;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.EventDataJson;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CheckBatteryJson implements EventDataJson {

    EventType eventType = EventType.CHECK_BATTERY;

    String serialNumber;
    Integer batteryLevel;
}
