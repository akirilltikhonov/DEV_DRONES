package com.musala.dev.drones.application.infra.api.rest.mapper;

import com.musala.dev.drones.api.dto.ImageDto;
import com.musala.dev.drones.api.dto.load.LoadImageDto;
import com.musala.dev.drones.application.domain.model.Image;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring"
        , unmappedSourcePolicy = ReportingPolicy.ERROR
        , unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ImageDtoMapper {

    @Mapping(target = "imageId", ignore = true)
    @Mapping(target = "s3Url", ignore = true)
    Image toImage(LoadImageDto image);

    @BeanMapping(ignoreUnmappedSourceProperties = {"imageBase64"})
    ImageDto toImageDto(Image Image);
}