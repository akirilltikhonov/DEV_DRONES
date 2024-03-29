package com.musala.dev.drones.application.domain.exception.notfound;

import com.musala.dev.drones.application.domain.exception.DomainException;
import org.springframework.http.HttpStatus;

public class DataNotFoundException extends DomainException {

    public DataNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}