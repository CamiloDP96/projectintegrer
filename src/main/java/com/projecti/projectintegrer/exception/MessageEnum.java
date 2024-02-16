package com.projecti.projectintegrer.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageEnum {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "The data of the user was not found"),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "The data was not found"),
    USER_EXISTS(HttpStatus.ALREADY_REPORTED, "The email was registered into the application previously"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "The email or password send are invalid");

    private final HttpStatus status;
    private final String message;
}
