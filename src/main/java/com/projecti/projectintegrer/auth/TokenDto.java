package com.projecti.projectintegrer.auth;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate) {

}
