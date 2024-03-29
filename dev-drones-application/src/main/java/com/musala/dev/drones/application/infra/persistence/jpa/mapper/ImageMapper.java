package com.musala.dev.drones.application.infra.persistence.jpa.mapper;

import com.musala.dev.drones.application.domain.model.Image;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.ImageMetadataJson;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ImageMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = {"imageBase64"})
    ImageMetadataJson toImageJson(Image image);

    @Mapping(target = "imageBase64", ignore = true)
    Image toImage(ImageMetadataJson image);
}
