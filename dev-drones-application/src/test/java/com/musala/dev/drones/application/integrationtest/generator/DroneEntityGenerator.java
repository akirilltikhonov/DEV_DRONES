package com.musala.dev.drones.application.integrationtest.generator;

import com.musala.dev.drones.application.integrationtest.generator.pattern.DronePattern;
import com.musala.dev.drones.application.infra.persistence.jpa.entity.DroneEntity;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.concurrent.ThreadLocalRandom;

import static org.jeasy.random.FieldPredicates.named;

public class DroneEntityGenerator {

    private static final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
                    .stringLengthRange(15, 20)
                    .randomize(named("weightLimit"), () -> ThreadLocalRandom.current().nextInt(0, 501))
                    .randomize(named("batteryCapacity"), () -> ThreadLocalRandom.current().nextInt(0, 101))
    );

    public static DroneEntity next() {
        return next(null);
    }

    public static DroneEntity next(DronePattern pattern) {
        var drone = random.nextObject(DroneEntity.class);
        if (pattern != null) {
            if (pattern.getSerialNumber() != null) {
                drone.setSerialNumber(pattern.getSerialNumber());
            }
            if (pattern.getModel() != null) {
                drone.setModel(pattern.getModel());
            }
            if (pattern.getWeightLimit() != null) {
                drone.setWeightLimit(pattern.getWeightLimit());
            }
            if (pattern.getBatteryCapacity() != null) {
                drone.setBatteryCapacity(pattern.getBatteryCapacity());
            }
            if (pattern.getState() != null) {
                drone.setState(pattern.getState());
            }
        }
        return drone;
    }
}