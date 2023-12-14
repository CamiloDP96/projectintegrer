package com.projecti.projectintegrer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientDto(
    Integer id,
    String name
    ) {
}