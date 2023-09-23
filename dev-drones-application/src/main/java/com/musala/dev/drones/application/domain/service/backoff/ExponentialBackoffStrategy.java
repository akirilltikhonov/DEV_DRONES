package com.musala.dev.drones.application.domain.service.backoff;

public class ExponentialBackoffStrategy implements BackoffStrategy {

    private final long initTime;
    private final float factor;
    private final long maxTime;
    private int level;

    /**
     * @param initTime in milliseconds for which the client is suspended after the first request
     * @param factor   is the base of the power by which the waiting time increases
     * @param maxTime  in milliseconds for which the client can be suspended
     *                 level is exponential power
     */
    public ExponentialBackoffStrategy(long initTime, float factor, long maxTime) {
        this.initTime = initTime;
        this.factor = factor;
        this.level = 0;
        this.maxTime = maxTime;
    }

    @Override
    public void reconfigure(boolean increaseDelay) {
        if (increaseDelay) {
            level++;
        } else {
            level = 0;
        }
    }

    @Override
    public long calculateBackoffTime() {
        if (level == 0) {
            return 0L;
        }

        long backoffTime = (long) (initTime * Math.pow(factor, level - 1));
        return Math.min(backoffTime, maxTime);
    }
}