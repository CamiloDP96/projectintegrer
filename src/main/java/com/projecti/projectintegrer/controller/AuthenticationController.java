package com.projecti.projectintegrer.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projecti.projectintegrer.domain.dto.AuthenticationDto;
import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public record AuthenticationController(
    AuthenticationService authenticationService
) {

    @PostMapping("/resgister")
    public ResponseEntity<?> register(@RequestBody ClientDto clientDto) throws ReservException {
        String token = authenticationService.register(clientDto);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

        @PostMapping("/authentication")
        public ResponseEntity<?> authenticate(@RequestBody AuthenticationDto authenticationDto) throws ReservException {
            String token = authenticationService.authenticate(authenticationDto);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
}