package com.musala.dev.drones.application.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Image {

    String imageId;
    String s3Url;
}
