package com.musala.dev.drones.application.infra.persistence.jpa.entity.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.impl.CheckBatteryJson;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "eventType",
        include = JsonTypeInfo.As.EXISTING_PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CheckBatteryJson.class, name = "CHECK_BATTERY"),
})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface EventDataJson {

    EventType getEventType();
}
