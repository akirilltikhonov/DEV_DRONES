package com.musala.dev.drones.application.domain.service;

import java.util.Base64;

import static org.springframework.util.ObjectUtils.isEmpty;

public final class Base64Coder {

    public static byte[] decodeBase64(String data) {
        if (isEmpty(data)) {
            return null;
        }

        try {
            return Base64.getDecoder().decode(data);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Object data must be base64 encoded byte array content");
        }
    }

    public static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}