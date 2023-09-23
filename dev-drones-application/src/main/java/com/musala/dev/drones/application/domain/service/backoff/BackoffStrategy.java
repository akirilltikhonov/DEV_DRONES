package com.musala.dev.drones.application.domain.service.backoff;

public interface BackoffStrategy {

    void reconfigure(boolean increaseDelay);

    long calculateBackoffTime();
}
