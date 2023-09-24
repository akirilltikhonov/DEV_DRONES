package com.musala.dev.drones.application.infra.persistence.jpa.entity;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ImageMetadataJson {

    UUID imageId;
    String s3Url;
}
