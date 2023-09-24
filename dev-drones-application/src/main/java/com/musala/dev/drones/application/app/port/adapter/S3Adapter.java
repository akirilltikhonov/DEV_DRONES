package com.musala.dev.drones.application.app.port.adapter;

import com.musala.dev.drones.application.domain.model.Image;

public interface S3Adapter {

    Image storeImage(String imageBase64);
}
