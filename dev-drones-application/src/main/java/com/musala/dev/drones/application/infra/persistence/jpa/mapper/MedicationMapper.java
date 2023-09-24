package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
//        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MedicationMapper {

    @Mapping(target = "drone", ignore = true)
    MedicationEntity toMedicationEntity(Medication medication);

    @Mapping(target = "drone", ignore = true)
    List<MedicationEntity> toMedicationEntities(List<Medication> medications);

    @Mapping(target = "image", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = {"drone"})
    Medication toMedication(MedicationEntity medicationEntity);

    @BeanMapping(ignoreUnmappedSourceProperties = {"drone"})
    List<Medication> toMedications(List<MedicationEntity> medicationEntity);
}
