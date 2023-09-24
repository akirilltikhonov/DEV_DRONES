package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Image;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.ImageMetadataJson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ImageMapper {

    ImageMetadataJson toImageJson(Image image);

    Image toImage(ImageMetadataJson image);
}
