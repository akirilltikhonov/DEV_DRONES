package com.musala.dev.drones.application.infra.persistence.jpa.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medications")
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonType.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serial_number")
    private DroneEntity drone;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "code")
    private String code;

    @Type(type = "json")
    @Column(name = "image_metadata", columnDefinition = "jsonb")
    private ImageMetadataJson image;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var that = (MedicationEntity) o;
        return medicationId != null && medicationId.equals(that.getMedicationId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
