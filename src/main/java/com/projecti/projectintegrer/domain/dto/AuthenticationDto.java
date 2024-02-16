package com.projecti.projectintegrer.domain.dto;

public record AuthenticationDto(
    String username,
    String passwordHash
) {
}
