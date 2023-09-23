package com.musala.dev.drones.application.infra.sheduler;

import com.musala.dev.drones.application.app.service.DroneService;
import com.musala.dev.drones.application.domain.service.backoff.BackoffStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DroneBatteryCheckSchedulerTest {

    private DroneBatteryCheckScheduler droneBatteryCheckScheduler;
    private final int nTreads = 1;
    private final int awaitTermination = 1;
    private final ExecutorService executorService = mock(ExecutorService.class);
    private final DroneService droneService = mock(DroneService.class);
    private final BackoffStrategy exponentialBackoffStrategy = mock(BackoffStrategy.class);

    @BeforeEach
    void setUp() {
        droneBatteryCheckScheduler = new DroneBatteryCheckScheduler(
                nTreads,
                awaitTermination,
                executorService,
                droneService,
                exponentialBackoffStrategy
        );
    }

    @Test
    void start() {
        droneBatteryCheckScheduler.start();
        verify(executorService, times(nTreads)).execute(any());
    }

    @Test
    void stopAwaitTerminationIsFalse() throws InterruptedException {
        droneBatteryCheckScheduler.stop();
        verify(executorService).shutdown();
        verify(executorService).awaitTermination(awaitTermination, TimeUnit.MILLISECONDS);
        verify(executorService).shutdownNow();
    }

    @Test
    void stopAwaitTerminationIsTrue() throws InterruptedException {
        doReturn(true).when(executorService)
                .awaitTermination(awaitTermination, TimeUnit.MILLISECONDS);

        droneBatteryCheckScheduler.stop();
        verify(executorService).shutdown();
        verify(executorService).awaitTermination(awaitTermination, TimeUnit.MILLISECONDS);
        verify(executorService, never()).shutdownNow();
    }

    @Test
    void stopAwaitTerminationException() throws InterruptedException {
        doThrow(new InterruptedException()).when(executorService)
                .awaitTermination(awaitTermination, TimeUnit.MILLISECONDS);

        droneBatteryCheckScheduler.stop();
        verify(executorService).shutdown();
        verify(executorService).awaitTermination(awaitTermination, TimeUnit.MILLISECONDS);
        verify(executorService).shutdownNow();
    }

    @Test
    void runWithExponentialBackoff() {
        doReturn(false)
                .when(executorService).isShutdown();
        doReturn(false).doReturn(true)
                .when(droneService).checkDronesBatteryLevel();
        doNothing().when(exponentialBackoffStrategy)
                .reconfigure(anyBoolean());
        doReturn(0L).doReturn(1L).doReturn(-1L)
                .when(exponentialBackoffStrategy).calculateBackoffTime();

        assertThatThrownBy(() -> droneBatteryCheckScheduler.runWithExponentialBackoff())
                .isInstanceOf(RuntimeException.class);

        verify(droneService, times(3)).checkDronesBatteryLevel();
        verify(exponentialBackoffStrategy).reconfigure(false);
        verify(exponentialBackoffStrategy, times(2)).reconfigure(true);
        verify(exponentialBackoffStrategy, times(3)).calculateBackoffTime();
    }

    @Test
    void runWithExponentialBackoffUntilShutdown() {
        doReturn(false).doReturn(false).doReturn(true)
                .when(executorService).isShutdown();
        doReturn(false).doReturn(true)
                .when(droneService).checkDronesBatteryLevel();
        doNothing().when(exponentialBackoffStrategy)
                .reconfigure(anyBoolean());
        doReturn(0L).doReturn(1L)
                .when(exponentialBackoffStrategy).calculateBackoffTime();

        droneBatteryCheckScheduler.runWithExponentialBackoff();

        verify(droneService, times(2)).checkDronesBatteryLevel();
        verify(exponentialBackoffStrategy).reconfigure(false);
        verify(exponentialBackoffStrategy).reconfigure(true);
        verify(exponentialBackoffStrategy, times(2)).calculateBackoffTime();
    }
}