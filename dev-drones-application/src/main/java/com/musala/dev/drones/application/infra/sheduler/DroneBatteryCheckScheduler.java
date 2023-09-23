package com.musala.dev.drones.application.infra.sheduler;

import com.musala.dev.drones.application.app.service.DroneCheckBatteryService;
import com.musala.dev.drones.application.domain.service.backoff.BackoffStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class DroneBatteryCheckScheduler {

    @Getter
    private final int nTreads;
    private final int awaitTermination;
    private final ExecutorService executorService;
    private final DroneCheckBatteryService droneCheckBatteryService;
    private final BackoffStrategy exponentialBackoffStrategy;

    @PostConstruct
    void start() {
        for (int i = 0; i < nTreads; i++) {
            executorService.execute(this::runWithExponentialBackoff);
        }
    }

    @PreDestroy
    void stop() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(awaitTermination, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    void runWithExponentialBackoff() {
        while (!executorService.isShutdown()) {
            var increaseDelay = droneCheckBatteryService.checkDronesBatteryLevel();
            exponentialBackoffStrategy.reconfigure(increaseDelay);
            long delay = exponentialBackoffStrategy.calculateBackoffTime();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
