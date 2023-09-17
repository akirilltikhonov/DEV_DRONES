package com.musala.dev.drones.application.domain.exception.badrequest;

public class LoadMedicationException extends BadRequestException {

    public LoadMedicationException(String serialNumber, Integer loaded) {
        super(String.format("Impossible to load medications by serialNumber = %s. %s gr is loaded now. Maximum is 500 gr",
                serialNumber, loaded));
    }
}