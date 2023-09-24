package com.musala.dev.drones.application.infra.adapter.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.musala.dev.drones.application.app.port.adapter.S3Adapter;
import com.musala.dev.drones.application.domain.model.Image;
import com.musala.dev.drones.application.domain.service.Base64Coder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3AdapterImpl implements S3Adapter {

    private final AmazonS3 s3Client;
    @Value("${s3.bucketName:bucketName}")
    private final String bucketName;

    @Override
    public Image storeImage(String imageBase64) {
        String key = UUID.randomUUID().toString();
        byte[] content = Base64Coder.decodeBase64(imageBase64);
        try (ByteArrayInputStream stream = new ByteArrayInputStream(content)) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(content.length);
            var request = new PutObjectRequest(bucketName, key, stream, metadata);
            s3Client.putObject(request);
            log.info("S3 Object successfully put: key={}, bucket={}", key, bucketName);
        } catch (IOException e) {
            log.error("An error occurred while S3 Object put: key={}, bucket={}, cause={}",
                    key, bucketName, e.getMessage());
        }

        var objectRequest = new GetObjectRequest(bucketName, key);
        var s3Object = s3Client.getObject(objectRequest);
        return Image.builder()
                .imageId(key)
                .s3Url(s3Object.getObjectContent().getHttpRequest().getURI().toString())
                .build();
    }
}
