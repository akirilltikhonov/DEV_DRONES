//package com.musala.dev.drones.application.integrationtest.layer.web.test.validation;
//
//import com.musala.dev.drones.api.dto.load.LoadMedicationRequestDto;
//import com.musala.dev.drones.application.integrationtest.layer.web.WebTest;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.UUID;
//import java.util.stream.Stream;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class DroneControllerLoadMedicationsValidationTest extends WebTest {
//
//    static Stream<File> isNotValidToLoadMedications() throws IOException {
//        return Arrays.stream(Objects.requireNonNull(
//                new ClassPathResource("json/load/notValid").getFile().listFiles()));
//    }
//
//    @ParameterizedTest
//    @MethodSource("isNotValidToLoadMedications")
//    void isNotValidToLoadMedications(File file) throws Exception {
//        String serialNumber = UUID.randomUUID().toString();
//        var request = objectMapper.readValue(file, LoadMedicationRequestDto.class);
//        String jsonRequest = objectMapper.writeValueAsString(request);
//
//        perform(put(DRONE_BASE_PATH + serialNumber + "/medications"), jsonRequest)
//                .andExpect(status().isBadRequest())
//                .andReturn().getResponse();
//    }
//
//    static Stream<File> isValidToLoadMedications() throws IOException {
//        return Arrays.stream(Objects.requireNonNull(
//                new ClassPathResource("json/load/valid").getFile().listFiles()));
//    }
//
//    @ParameterizedTest
//    @MethodSource("isValidToLoadMedications")
//    void isValidToLoadMedications(File file) throws Exception {
//        String serialNumber = UUID.randomUUID().toString();
//        var request = objectMapper.readValue(file, LoadMedicationRequestDto.class);
//        String jsonRequest = objectMapper.writeValueAsString(request);
//
//        perform(put(DRONE_BASE_PATH + serialNumber + "/medications"), jsonRequest)
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//    }
//}