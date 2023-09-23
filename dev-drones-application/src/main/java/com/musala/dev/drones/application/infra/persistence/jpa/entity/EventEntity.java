package com.musala.dev.drones.application.infra.persistence.jpa.entity;

import com.musala.dev.drones.application.domain.model.enums.EventType;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.json.EventDataJson;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@TypeDef(name = "pg_enum", typeClass = PostgreSQLEnumType.class)
@TypeDef(name = "json", typeClass = JsonType.class)
public class EventEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "event_id")
    private UUID eventId;

    @Type(type = "pg_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @Type(type = "json")
    @Column(name = "event_data", columnDefinition = "jsonb")
    private EventDataJson eventData;
}
