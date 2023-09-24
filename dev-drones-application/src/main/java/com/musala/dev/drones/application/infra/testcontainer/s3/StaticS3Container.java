package com.musala.dev.drones.application.infra.testcontainer.s3;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class StaticS3Container {
    public static LocalStackContainer getContainer() {
        return LazyS3Container.INSTANCE;
    }

    private static class LazyS3Container {
        private static final LocalStackContainer INSTANCE = makeContainer();

        private static LocalStackContainer makeContainer() {
            var localstack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3"))
                    .withServices(LocalStackContainer.Service.S3);
            localstack.start();
            return localstack;
        }
    }
}
