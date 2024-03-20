package com.musala.dev.drones.api.dto.error;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    void errorResponse() {
        var errorResponse = new ErrorResponse("message");
    }
}