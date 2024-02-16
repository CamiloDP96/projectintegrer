package com.projecti.projectintegrer.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ReservException extends Exception{

    private final HttpStatus status;
    private final String message;

    public ReservException(MessageEnum eMessage){
        this.status = eMessage.getStatus();
        this.message = eMessage.getMessage();
    }

}
