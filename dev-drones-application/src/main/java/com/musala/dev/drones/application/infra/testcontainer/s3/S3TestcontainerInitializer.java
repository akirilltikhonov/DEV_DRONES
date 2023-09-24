package com.musala.dev.drones.application.infra.testcontainer.s3;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

public class S3TestcontainerInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        final var localstack = StaticS3Container.getContainer();
        var env = context.getEnvironment();
        env.getPropertySources().addFirst(new MapPropertySource(
                "s3",
                Map.of(
                        "s3.endpoint", localstack.getEndpoint().toString(),
                        "s3.region", localstack.getRegion(),
                        "s3.accessKey", localstack.getAccessKey(),
                        "s3.secretKey", localstack.getSecretKey()
                )
        ));
    }
}