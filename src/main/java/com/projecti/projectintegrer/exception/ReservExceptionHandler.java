package com.projecti.projectintegrer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ReservExceptionHandler {

    @ExceptionHandler(ReservException.class)
    public ResponseEntity<?> handleReservExeption(ReservException exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(response, exception.getStatus());
    }
}
