package com.musala.dev.drones.application.infra.s3.config;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitS3BucketConfig {

    private final AmazonS3 s3Client;
    @Value("${s3.bucketName}")
    private final String bucketName;

    @PostConstruct
    void createBucket() {
        s3Client.createBucket(bucketName);
    }
}
