package com.projecti.projectintegrer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/client")
public record ClientController(
    ClientService clientService
) {
    @PostMapping("/createClient")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerClient(@RequestBody ClientDto clientDto){
        clientService.createClient(clientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("clientList/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> showClients(
        @PathVariable("offset") Integer offset,
        @PathVariable("limit") Integer limit) throws ReservException {
            List<ClientDto> users = clientService.getAllClients(offset, limit);
            return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("client/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findClientById(@PathVariable("id") Integer id) throws ReservException {
        ClientDto client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.FOUND);
    }

    @DeleteMapping("delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id) throws ReservException {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/client")
    @SecurityRequirement(name = "bearerAuth")
        public ResponseEntity<?> updateClient(@PathVariable("id") Integer id, @RequestBody ClientDto clientDto) throws ReservException{
            clientService.updateClient(id, clientDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
