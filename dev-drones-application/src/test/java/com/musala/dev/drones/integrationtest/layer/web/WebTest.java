package com.musala.dev.drones.integrationtest.layer.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(TestWebConfiguration.class)
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

    protected ResultActions perform(String path, String jsonRequest) throws Exception {
        return mockMvc.perform(post(DRONE_BASE_PATH + path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));
    }
}