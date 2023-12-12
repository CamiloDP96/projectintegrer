package com.projecti.projectintegrer.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoomDto(
    Integer id,
    Integer room,
    LocalDate searcheed_time,
    String status
    ) {
}
