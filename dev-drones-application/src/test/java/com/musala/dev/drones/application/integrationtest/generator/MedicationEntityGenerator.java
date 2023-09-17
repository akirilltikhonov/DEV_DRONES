package com.musala.dev.drones.application.integrationtest.generator;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import com.musala.dev.drones.application.integrationtest.generator.pattern.MedicationPattern;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.concurrent.ThreadLocalRandom;

import static org.jeasy.random.FieldPredicates.named;

public class MedicationEntityGenerator {

    private static final EasyRandom random = new EasyRandom(
            new EasyRandomParameters()
                    .seed(1L)
                    .excludeField(named("medicationId")
                            .or(named("drone"))
                    )
                    .stringLengthRange(15, 20)
                    .randomize(named("weight"), () -> ThreadLocalRandom.current().nextInt(0, 501))
    );

    public static MedicationEntity next() {
        return next(null);
    }

    public static MedicationEntity next(MedicationPattern pattern) {
        var medication = random.nextObject(MedicationEntity.class);
        if (pattern != null) {
            if (pattern.getName() != null) {
                medication.setName(pattern.getName());
            }
            if (pattern.getWeight() != null) {
                medication.setWeight(pattern.getWeight());
            }
            if (pattern.getCode() != null) {
                medication.setCode(pattern.getCode());
            }
        }
        return medication;
    }
}