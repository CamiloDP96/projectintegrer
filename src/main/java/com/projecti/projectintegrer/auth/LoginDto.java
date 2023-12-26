package com.projecti.projectintegrer.auth;

public class LoginDto {

    private final String client;

    private final String password;


    public LoginDto(String client, String password) {
        this.client = client;
        this.password = password;
    }

    public String getClient() {
        return client;
    }

    public String getPassword() {
        return password;
    }

}
