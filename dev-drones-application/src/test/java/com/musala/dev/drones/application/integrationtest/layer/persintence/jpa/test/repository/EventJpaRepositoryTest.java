package com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.test.repository;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.repository.EventJpaRepository;
import com.musala.dev.drones.application.integrationtest.generator.EventEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.EventPattern;
import com.musala.dev.drones.application.integrationtest.generator.pattern.json.CheckBatteryJsonPattern;
import com.musala.dev.drones.application.integrationtest.layer.persintence.jpa.JpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EventJpaRepositoryTest extends JpaTest {

    @Autowired
    private EventJpaRepository eventJpaRepository;

    @Test
    void save() {
        var saved = eventJpaRepository.save(EventEntityGenerator.next());
        eventJpaRepository.findById(saved.getEventId()).orElseThrow();
    }

    @Test
    void saveWithPattern() {
        LocalDateTime eventTime = LocalDateTime.now().minusMonths(1);
        String serialNumber = UUID.randomUUID().toString();
        Integer batteryLevel = 10;
        var saved = eventJpaRepository.save(EventEntityGenerator.next(EventPattern.builder()
                .eventType(EventType.CHECK_BATTERY)
                .eventTime(eventTime)
                .dataPattern(CheckBatteryJsonPattern.builder()
                        .serialNumber(serialNumber)
                        .batteryLevel(batteryLevel)
                        .build())
                .build()));

        var eventEntityWithPattern = eventJpaRepository.findById(saved.getEventId()).orElseThrow();
        assertThat(eventEntityWithPattern)
                .usingRecursiveComparison()
                .ignoringFields("eventTime")
                .isEqualTo(saved);
        assertThat(eventEntityWithPattern.getEventTime().truncatedTo(ChronoUnit.MILLIS))
                .isEqualTo(saved.getEventTime().truncatedTo(ChronoUnit.MILLIS));
    }
}