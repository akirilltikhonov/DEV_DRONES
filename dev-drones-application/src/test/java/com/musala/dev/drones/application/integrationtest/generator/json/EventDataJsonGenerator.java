package com.musala.dev.drones.application.integrationtest.generator.json;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.EventDataJson;
import com.musala.dev.drones.application.integrationtest.generator.pattern.json.EventDataJsonPattern;

public interface EventDataJsonGenerator {

    EventDataJson next(EventDataJsonPattern pattern);

    boolean isCompatible(EventType eventType);
}
