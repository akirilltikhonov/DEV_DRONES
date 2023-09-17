package com.musala.dev.drones.application.integrationtest.generator.test;

import com.musala.dev.drones.application.infra.persistence.jpa.entity.MedicationEntity;
import com.musala.dev.drones.application.integrationtest.generator.MedicationEntityGenerator;
import com.musala.dev.drones.application.integrationtest.generator.pattern.MedicationPattern;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MedicationEntityGeneratorTest {

    private void assertNextMedicationEntity(MedicationEntity medication) {
        assertThat(medication).isNotNull();
        assertThat(medication).hasNoNullFieldsOrPropertiesExcept("medicationId", "drone");
        assertThat(medication).hasAllNullFieldsOrPropertiesExcept("name", "weight", "code");
    }

    @Test
    void next() {
        assertNextMedicationEntity(MedicationEntityGenerator.next());
    }

    @Test
    void nextWithNullPattern() {
        assertNextMedicationEntity(MedicationEntityGenerator.next(null));
    }

    @Test
    void nextWithPattern() {
        final var pattern = MedicationPattern.builder()
                .name("name")
                .weight(500)
                .code("code")
                .build();
        final var medication = MedicationEntityGenerator.next(pattern);
        assertThat(pattern)
                .usingRecursiveComparison()
                .isEqualTo(medication);
    }

    @Test
    void nextWithPatternAssertionError() {
        final MedicationPattern pattern = MedicationPattern.builder().build();
        final MedicationEntity medication = MedicationEntityGenerator.next(pattern);
        assertThatThrownBy(() -> assertThat(pattern).usingRecursiveComparison().isEqualTo(medication))
                .isInstanceOf(AssertionError.class);
    }
}