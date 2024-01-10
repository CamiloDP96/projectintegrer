package com.projecti.projectintegrer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projecti.projectintegrer.controller.health.HealthController;

public class HealthControllerTest {
    private final HealthController healthController = new HealthController();

    @Test
    void testCheckEndpoint() {
        ResponseEntity<String> response = healthController.check();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("API Working OK!", response.getBody());
    }
}
