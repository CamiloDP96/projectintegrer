package com.projecti.projectintegrer.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.projecti.projectintegrer.domain.entities.UserRoleEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientDto(
    Integer id,
    String name,
    String username,
    String email,
    String password,
    UserRoleEnum role,
    Boolean enable) {

}