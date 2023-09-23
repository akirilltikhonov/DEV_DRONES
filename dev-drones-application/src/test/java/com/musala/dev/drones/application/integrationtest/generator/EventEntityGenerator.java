package com.musala.dev.drones.application.integrationtest.generator;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.EventEntity;
import com.musala.dev.drones.application.integrationtest.generator.json.EventDataJsonGeneratorResolver;
import com.musala.dev.drones.application.integrationtest.generator.pattern.EventPattern;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import static org.jeasy.random.FieldPredicates.named;

public class EventEntityGenerator {

    private static final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .stringLengthRange(10, 20)
                    .excludeField(named("eventId")
                            .or(named("eventData"))
                    )
    );

    private static final EventDataJsonGeneratorResolver resolver = new EventDataJsonGeneratorResolver();

    public static EventEntity next() {
        return next(null);
    }

    public static EventEntity next(EventPattern pattern) {
        EventEntity event = random.nextObject(EventEntity.class);
        event.setEventData(resolver.resolve(event.getEventType()).next(null));
        if (pattern != null) {
            if (pattern.getEventType() != null) {
                event.setEventType(pattern.getEventType());
            }
            if (pattern.getEventTime() != null) {
                event.setEventTime(pattern.getEventTime());
            }
            if (pattern.getDataPattern() != null) {
                event.setEventData(resolver.resolve(event.getEventType()).next(pattern.getDataPattern()));
            }
        }
        return event;
    }
}