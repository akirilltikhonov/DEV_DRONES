package com.musala.dev.drones.application.infra.persistence.jpa.entity;

import com.musala.dev.drones.application.domain.model.enums.Model;
import com.musala.dev.drones.application.domain.model.enums.State;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "drone",
            orphanRemoval = true)
    private List<MedicationEntity> medications = new ArrayList<>();

    public void addMedication(MedicationEntity ingredient) {
        medications.add(ingredient);
        ingredient.setDrone(this);
    }

    public void removeMedication(MedicationEntity ingredient) {
        medications.remove(ingredient);
        ingredient.setDrone(null);
    }

    public void linkMedications(List<MedicationEntity> ingredients) {
        ingredients.forEach(i -> i.setDrone(this));
    }
}
