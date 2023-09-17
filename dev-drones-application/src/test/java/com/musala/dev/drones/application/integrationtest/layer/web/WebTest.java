package com.musala.dev.drones.application.integrationtest.layer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.dev.drones.application.infra.api.rest.controller.DroneController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@MockBean({
        DroneController.class,
})
public class WebTest {

    protected static final String DRONE_BASE_PATH = "/dev-drones/";

    @Autowired
    private ApplicationContext context;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(context).isNotNull();
    }

    protected ResultActions perform(MockHttpServletRequestBuilder builder, String jsonRequest) throws Exception {
        return mockMvc.perform(builder
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
    }
}