package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.app.port.EventRepository;
import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.EventMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.EventJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EventJpaRepository eventJpaRepository;
    private final EventMapper eventMapper;

    @Override
    public void logEvents(List<Event> events) {
        var eventEntities = eventMapper.toEventEntities(events);
        eventJpaRepository.saveAll(eventEntities);
    }
}
