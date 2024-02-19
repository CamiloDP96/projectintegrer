package com.projecti.projectintegrer.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.AuthenticationDto;
import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.ClientMapper;
import com.projecti.projectintegrer.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public record AuthenticationService(
    ClientRepository clientRepository,
    JwtService jwtService,
    PasswordEncoder passwordEncoder,
    ClientMapper mapper,
    AuthenticationManager authenticationManager
) {

    public String register(ClientDto clientDto) throws ReservException {
        try {
            Client client = mapper.toEntity(clientDto);
            client.setPassword(passwordEncoder.encode(clientDto.password()));
            client.setEnable(true);
            clientRepository.save(client);
            return jwtService.generateToken(client);
        } catch (DataIntegrityViolationException e) {
            throw new ReservException(MessageEnum.USER_EXISTS);
        }
    }

    public String authenticate(AuthenticationDto authenticationDto) throws ReservException {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationDto.username(), authenticationDto.password())
        );
        Client client = clientRepository.findByUsername(authenticationDto.username())
            .orElseThrow(() -> new ReservException(MessageEnum.INVALID_CREDENTIALS));
        return jwtService.generateToken(client);
    }
}
