package com.musala.dev.drones.application.domain.service.factory.event.impl;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.domain.model.data.impl.CheckBattery;
import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.domain.service.factory.event.EventFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CheckBatteryFactory implements EventFactory {

    @Override
    public Event buildEvent(Drone drone) {
        return Event.builder()
                .eventType(EventType.CHECK_BATTERY)
                .eventTime(LocalDateTime.now())
                .eventData(CheckBattery.builder()
                        .serialNumber(drone.getSerialNumber())
                        .batteryLevel(drone.getBatteryLevel())
                        .build())
                .build();
    }

    @Override
    public List<Event> buildEvents(List<Drone> drones) {
        return drones.stream()
                .map(this::buildEvent)
                .toList();
    }
}
