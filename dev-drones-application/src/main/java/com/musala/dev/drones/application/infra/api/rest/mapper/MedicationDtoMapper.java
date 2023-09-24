package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationDto;
import com.musala.dev.drones.application.domain.model.Medication;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
        , injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface MedicationDtoMapper {

    @Mapping(target = "medicationId", ignore = true)
    @Mapping(target = "image", ignore = true)
    @BeanMapping(ignoreUnmappedSourceProperties = {"image"})
    Medication toMedication(LoadMedicationDto medication);

    List<Medication> toMedications(List<LoadMedicationDto> medications);

    @BeanMapping(ignoreUnmappedSourceProperties = {"image"})
    MedicationDto toMedication(Medication medication);

    List<MedicationDto> toMedicationDtos(List<Medication> medications);
}