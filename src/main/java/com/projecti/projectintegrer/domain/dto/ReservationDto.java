package com.projecti.projectintegrer.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projecti.projectintegrer.domain.entities.StatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationDto(
    Integer id,
    Integer quantityPeople,
    LocalDate checkIn,
    LocalDate checkOut,
    StatusEnum state
    ) {
}
