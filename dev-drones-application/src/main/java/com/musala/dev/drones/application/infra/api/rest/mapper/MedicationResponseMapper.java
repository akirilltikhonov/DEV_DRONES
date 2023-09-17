package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.MedicationDto;
import com.musala.dev.drones.application.domain.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MedicationResponseMapper {

    MedicationDto toMedication(Medication medication);

    List<MedicationDto> toMedications(List<Medication> medication);
}