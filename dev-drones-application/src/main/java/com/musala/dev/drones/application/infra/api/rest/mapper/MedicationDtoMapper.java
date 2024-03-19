package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.api.dto.load.LoadMedicationDto;
import com.musala.dev.drones.application.domain.model.Medication;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
        , injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , uses = {
                ImageDtoMapper.class
}
)
public interface MedicationDtoMapper {

    @Mapping(target = "medicationId", ignore = true)
    Medication toMedication(LoadMedicationDto medication);

    List<Medication> toMedications(List<LoadMedicationDto> medications);

    MedicationDto toMedication(Medication medication);

    List<MedicationDto> toMedicationDtos(List<Medication> medications);
}