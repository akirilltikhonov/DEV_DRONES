package com.musala.dev.drones.application.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Image {

    UUID imageId;
    String s3Url;
    String imageBase64;
}
