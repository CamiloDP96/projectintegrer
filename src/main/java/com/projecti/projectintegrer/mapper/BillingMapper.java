package com.projecti.projectintegrer.mapper;

import java.util.List;


import org.mapstruct.Mapper;

import com.projecti.projectintegrer.domain.dto.BillingDto;
import com.projecti.projectintegrer.domain.entities.Billing;

@Mapper(componentModel = "spring")
public interface BillingMapper {

    Billing toEntity(BillingDto dto);

    BillingDto toDto(Billing entity);

    List<Billing> toEntityList(List<BillingDto> dtoList);

    List<BillingDto> toDtoList(List<Billing> entityList);
}
