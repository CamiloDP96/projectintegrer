package com.projecti.projectintegrer.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoomDto(
    Integer id,
    Integer room,
    String benefits,
    String type,
    Double pricePerNigth
    ) {
}
