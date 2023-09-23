package com.musala.dev.drones.application.domain.service.factory.event;

import com.musala.dev.drones.application.domain.model.Drone;
import com.musala.dev.drones.application.domain.model.Event;

import java.util.List;

public interface EventFactory {

    Event buildEvent(Drone drone);

    List<Event> buildEvents(List<Drone> drones);
}
