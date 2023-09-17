package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Medication;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MedicationMapper {

    @Mapping(target = "drone", ignore = true)
    MedicationEntity toMedicationEntity(Medication ingredient);

    @BeanMapping(ignoreUnmappedSourceProperties = {"drone"})
    Medication toMedication(MedicationEntity ingredientEntity);
}
