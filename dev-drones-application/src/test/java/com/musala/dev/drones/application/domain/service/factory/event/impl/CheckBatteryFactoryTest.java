package com.musala.dev.drones.application.domain.service.factory.event.impl;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.domain.model.data.impl.CheckBattery;
import com.musala.dev.drones.application.domain.model.enums.EventType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CheckBatteryFactoryTest {

    private final CheckBatteryFactory checkBatteryFactory = new CheckBatteryFactory();

    @Test
    void buildEvent() {
        var drone = Drone.builder()
                .serialNumber("serialNumber")
                .batteryLevel(100)
                .build();
        var event = Event.builder()
                .eventType(EventType.CHECK_BATTERY)
                .eventTime(LocalDateTime.now())
                .eventData(CheckBattery.builder()
                        .serialNumber(drone.getSerialNumber())
                        .batteryLevel(drone.getBatteryLevel())
                        .build())
                .build();
        assertThat(checkBatteryFactory.buildEvents(List.of(drone)))
                .usingRecursiveComparison()
                .ignoringFields("eventTime")
                .isEqualTo(List.of(event));
    }
}