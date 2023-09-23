package com.musala.dev.drones.application.domain.model;

import com.musala.dev.drones.application.domain.model.data.EventData;
import com.musala.dev.drones.application.domain.model.enums.EventType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Event {

    UUID eventId;
    EventType eventType;
    LocalDateTime eventTime;
    EventData eventData;
}
