package com.projecti.projectintegrer.mapper;

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.domain.entities.Reservation;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation toEntity(ReservationDto dto);

    ReservationDto toDto(Reservation entity);

    List<Reservation> toEntityList(List<ReservationDto> dtoList);

    List<ReservationDto> toDtoList(List<Reservation> entityList);
}