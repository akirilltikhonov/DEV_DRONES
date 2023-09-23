package com.musala.dev.drones.application.infra.sheduler.config;

import com.musala.dev.drones.application.app.service.DroneCheckBatteryService;
import com.musala.dev.drones.application.domain.service.backoff.ExponentialBackoffStrategy;
import com.musala.dev.drones.application.infra.sheduler.DroneBatteryCheckScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class DroneBatteryCheckSchedulerConfiguration {

    @Bean
    public DroneBatteryCheckScheduler droneBatteryCheckScheduler(
            @Value("${drone-executor.await-termination:5000}") int awaitTermination,
            @Value("${drone-executor.number-of-threads:2}") int nTreads,
            @Value("${drone-executor.exponential-backoff.init-time:500}") long initTime,
            @Value("${drone-executor.exponential-backoff.factor-time:2}") float factor,
            @Value("${drone-executor.exponential-backoff.max-time:60000}") long maxTime,
            DroneCheckBatteryService droneCheckBatteryService
    ) {
        var backoffStrategy = new ExponentialBackoffStrategy(initTime, factor, maxTime);
        var executorService = Executors.newFixedThreadPool(nTreads);
        return new DroneBatteryCheckScheduler(nTreads, awaitTermination, executorService, droneCheckBatteryService, backoffStrategy);
    }
}