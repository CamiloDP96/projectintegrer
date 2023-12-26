package com.projecti.projectintegrer.exception;

public class TokenExpiredException extends ServerErrorException {

    public TokenExpiredException() {
        super("Token expired or malformed");
    }

}
