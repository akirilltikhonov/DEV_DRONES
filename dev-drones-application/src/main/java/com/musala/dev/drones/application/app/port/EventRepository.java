package com.musala.dev.drones.application.app.port;

import com.musala.dev.drones.application.domain.model.Event;

import java.util.List;

public interface EventRepository {

    void logEvents(List<Event> events);
}
