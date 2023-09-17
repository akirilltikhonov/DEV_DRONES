package com.musala.dev.drones.application.domain.exception.badrequest;

import com.musala.dev.drones.application.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends DomainException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}