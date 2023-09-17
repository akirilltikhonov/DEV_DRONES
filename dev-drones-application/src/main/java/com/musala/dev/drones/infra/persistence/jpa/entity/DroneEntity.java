package com.musala.dev.drones.infra.persistence.jpa.entity;

import com.musala.dev.drones.domain.model.enums.Model;
import com.musala.dev.drones.domain.model.enums.State;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drones")
@Getter
@Setter
@TypeDef(name = "pg_enum", typeClass = PostgreSQLEnumType.class)
public class DroneEntity {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @Type(type = "pg_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "model")
    private Model model;

    @Column(name = "weight_limit")
    private Integer weightLimit;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @Type(type = "pg_enum")
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
}
