package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.EventEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , uses = {
        EventDataMapper.class
}
)
public interface EventMapper {

    EventEntity toEventEntity(Event event);

    List<EventEntity> toEventEntities(List<Event> events);
}
