package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.data.EventData;
import com.musala.dev.drones.application.domain.model.data.impl.CheckBattery;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.EventDataJson;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.impl.CheckBatteryJson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.SubclassExhaustiveStrategy;
import org.mapstruct.SubclassMapping;

@Mapper(
        componentModel = "spring"
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , subclassExhaustiveStrategy = SubclassExhaustiveStrategy.RUNTIME_EXCEPTION
)
public interface EventDataMapper {

    @SubclassMapping(target = CheckBatteryJson.class, source = CheckBattery.class)
    EventDataJson toEventDataJson(EventData eventData);
}
