package com.example.Labo3.utilidad;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class GestorRespuestas {
    public static final String STATUS_KEY = "status";
    public static final String DATA_KEY = "data";
    public static final String MESSAGE_KEY = "message";

    public static ResponseEntity<Object> response(
            HttpStatus status,
            Object response,
            String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS_KEY, status.value());
        map.put(DATA_KEY, response);
        map.put(MESSAGE_KEY, message);

        return new ResponseEntity<>(map, status);
    }
}