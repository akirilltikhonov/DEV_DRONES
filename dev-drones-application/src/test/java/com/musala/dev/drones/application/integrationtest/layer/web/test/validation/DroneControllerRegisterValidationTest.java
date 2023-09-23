package com.musala.dev.drones.application.integrationtest.layer.web.test.validation;

import com.musala.dev.drones.api.dto.RegisterDroneDto;
import com.musala.dev.drones.application.integrationtest.layer.web.WebTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DroneControllerRegisterValidationTest extends WebTest {

    static Stream<File> isNotValidToRegister() throws IOException {
        return Arrays.stream(Objects.requireNonNull(
                new ClassPathResource("json/register/notValid").getFile().listFiles()));
    }

    @ParameterizedTest
    @MethodSource("isNotValidToRegister")
    void isNotValidToRegister(File file) throws Exception {
        var request = objectMapper.readValue(file, RegisterDroneDto.class);
        String jsonRequest = objectMapper.writeValueAsString(request);

        perform(post(DRONE_BASE_PATH + "register"), jsonRequest)
                .andExpect(status().isBadRequest())
                .andReturn().getResponse();
    }

    static Stream<File> isValidToRegister() throws IOException {
        return Arrays.stream(Objects.requireNonNull(
                new ClassPathResource("json/register/valid").getFile().listFiles()));
    }

    @ParameterizedTest
    @MethodSource("isValidToRegister")
    void isValidToRegister(File file) throws Exception {
        var request = objectMapper.readValue(file, RegisterDroneDto.class).toBuilder()
                .serialNumber(UUID.randomUUID().toString())
                .build();
        String jsonRequest = objectMapper.writeValueAsString(request);

        perform(post(DRONE_BASE_PATH + "register"), jsonRequest)
                .andExpect(status().isOk())
                .andReturn().getResponse();
    }
}