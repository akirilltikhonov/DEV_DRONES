package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
import com.musala.dev.drones.application.domain.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MedicationRequestMapper {

    @Mapping(target = "medicationId", ignore = true)
    Medication toMedication(LoadMedicationRequestDto requestDto);

    List<Medication> toMedications(List<LoadMedicationRequestDto> requestDtos);
}