package com.musala.dev.drones.application.domain.service.backoff;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExponentialBackoffStrategyTest {

    @Test
    void scenarioWithFirstStrategy() {
        final long initTime = 500L;
        final float factor = 2f;
        final long maxTime = 60000L;
        final var strategy1 = new ExponentialBackoffStrategy(initTime, factor, 60000L);

        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime);

        strategy1.reconfigure(false);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime);

        strategy1.reconfigure(true);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime);

        strategy1.reconfigure(true);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime * (long) Math.pow(factor, 1));

        strategy1.reconfigure(true);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime * (long) Math.pow(factor, 2));

        strategy1.reconfigure(true);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime * (long) Math.pow(factor, 3));

        for (int i = 0; i < 10; i++) {
            strategy1.reconfigure(true);
        }
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(maxTime);

        strategy1.reconfigure(false);
        assertThat(strategy1.calculateBackoffTime()).isEqualTo(initTime);
    }

    @Test
    void scenarioWithSecondStrategy() {
        final long initTime = 500L;
        final float factor = 1.5f;
        final long maxTime = 60000L;
        final var strategy2 = new ExponentialBackoffStrategy(initTime, factor, maxTime);

        assertThat(strategy2.calculateBackoffTime()).isEqualTo(initTime);

        strategy2.reconfigure(false);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo(initTime);

        strategy2.reconfigure(true);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo(initTime);

        strategy2.reconfigure(true);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo((long) (initTime * Math.pow(factor, 1)));

        strategy2.reconfigure(true);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo((long) (initTime * Math.pow(factor, 2)));

        strategy2.reconfigure(true);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo((long) (initTime * Math.pow(factor, 3)));

        for (int i = 0; i < 10; i++) {
            strategy2.reconfigure(true);
        }
        assertThat(strategy2.calculateBackoffTime()).isEqualTo(maxTime);

        strategy2.reconfigure(false);
        assertThat(strategy2.calculateBackoffTime()).isEqualTo(initTime);
    }
}