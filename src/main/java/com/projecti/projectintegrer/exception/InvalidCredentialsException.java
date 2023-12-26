package com.projecti.projectintegrer.exception;

public class InvalidCredentialsException extends ServerErrorException {

    public InvalidCredentialsException() {
        super("invalid username or password");
    }
}
