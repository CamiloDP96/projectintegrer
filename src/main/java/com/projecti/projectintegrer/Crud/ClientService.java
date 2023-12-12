package com.projecti.projectintegrer.Crud;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.repositories.ClientRepository;

public record ClientService(ClientRepository clientRepository) {
     // Create
    public void createClient(ClientDto clientDto){
        Client client = Client.builder()
            .name(clientDto.name())
            .build();
        ClientRepository.save(client);
    }

    // Read
    Client getClientById(String userId);

    List<Client> getAllClients();

    // Update
    void updateClient(String clientId, Client updatedclient);

    // Delete
    void deleteClient(String clientId);
}
