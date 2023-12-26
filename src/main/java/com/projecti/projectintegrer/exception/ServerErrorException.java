package com.projecti.projectintegrer.exception;

public abstract class ServerErrorException extends RuntimeException {

    public ServerErrorException(String message) {
        super(message);
    }
}
