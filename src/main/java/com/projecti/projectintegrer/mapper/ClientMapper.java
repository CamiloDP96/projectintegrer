package com.projecti.projectintegrer.mapper;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper extends IBaseMapper  {

    Client toEntity(ClientDto dto);

    ClientDto toDto(Client entity);

    List<Client> toEntityList(List<ClientDto> dtoList);

    List<ClientDto> toDtoList(List<Client> entityList);
}
