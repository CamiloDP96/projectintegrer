package com.projecti.projectintegrer.mapper;

import java.util.List;

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.domain.entities.Room;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends IBaseMapper {

    Room toEntity(RoomDto dto);

    RoomDto toDto(Room entity);

    List<Room> toEntityList(List<RoomDto> dtoList);

    List<RoomDto> toDtoList(List<Room> entityList);
}
