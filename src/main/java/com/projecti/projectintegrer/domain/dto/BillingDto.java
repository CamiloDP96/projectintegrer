package com.projecti.projectintegrer.domain.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillingDto(
    Integer id,
    LocalDateTime date,
    Integer days,
    Double total_amount
    ) {
}
