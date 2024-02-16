package com.projecti.projectintegrer.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponse(
    String message
) {
}
