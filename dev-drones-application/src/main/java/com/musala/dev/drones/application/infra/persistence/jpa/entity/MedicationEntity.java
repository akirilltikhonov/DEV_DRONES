package com.musala.dev.drones.application.infra.persistence.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medications")
@Getter
@Setter
public class MedicationEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medication_sequence"
    )
    @GenericGenerator(
            name = "medication_sequence",
            strategy = "io.hypersistence.utils.hibernate.id.BatchSequenceGenerator",
            parameters = {
                    @Parameter(name = "sequence", value = "medication_sequence"),
                    @Parameter(name = "fetch_size", value = "5")
            }
    )
    @Column(name = "medication_id")
    private Long medicationId;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "code")
    private String code;
}
