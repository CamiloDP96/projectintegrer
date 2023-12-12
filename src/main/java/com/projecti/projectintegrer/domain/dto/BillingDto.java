package com.projecti.projectintegrer.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BillingDto(
    Integer id,
    Double total_amount
    ) {
}
