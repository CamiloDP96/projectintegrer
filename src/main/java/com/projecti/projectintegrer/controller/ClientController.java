package com.projecti.projectintegrer.controller;

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

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.service.ClientService;

@RestController
@RequestMapping("api/v1/client")
public record ClientController(
    ClientService clientService
) {
    @PostMapping("/createClient")
    public ResponseEntity<?> registerClient(@RequestBody ClientDto clientDto){
        clientService.createClient(clientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("clientList")
    public ResponseEntity<?> showClients(){
        return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/client")
        public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto){
            clientService.updateClient(clientDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
