package com.musala.dev.drones.application.infra.persistence.jpa.repository.impl;

import com.musala.dev.drones.application.domain.model.Event;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.EventEntity;
import com.musala.dev.drones.application.infra.persistence.jpa.mapper.EventMapper;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.EventJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventRepositoryImplTest {

    @InjectMocks
    private EventRepositoryImpl eventRepository;
    @Mock
    private EventJpaRepository eventJpaRepository;
    @Mock
    private EventMapper eventMapper;

    @Test
    void logEvents() {
        var events = List.of(Event.builder().build());

        var eventEntities = List.of(new EventEntity());
        doReturn(eventEntities).when(eventMapper).toEventEntities(events);

        eventRepository.logEvents(events);
        verify(eventJpaRepository).saveAll(eventEntities);
    }
}