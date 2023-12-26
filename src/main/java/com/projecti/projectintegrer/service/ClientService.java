package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.repositories.ClientRepository;

@Service
public record ClientService(ClientRepository clientRepository) {
     // Create
    public void createClient(ClientDto clientDto){
        Client client = Client.builder()
            .name(clientDto.name())
            .build();

        clientRepository.save(client);
    }

    // Update
    public void updateClient(ClientDto clientDto){
        Client client = clientRepository.findById(clientDto.id())
            .orElseThrow(() -> new RuntimeException("Client not found..."));
        updateClientData(client, clientDto);
        clientRepository.save(client);
    }

    private void updateClientData(
        Client client,
        ClientDto clientDto
        ){
            client.setId(clientDto.id());
            client.setName(clientDto.name());
        }

        // Delete
        public void deleteClient(Integer clientId){
            Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"));
            clientRepository.delete(client);
        }

        public List<Client> getAllClients(){
            return clientRepository.findAll();
        }
        public Optional<Client> getClientById (Integer clientId){
            return clientRepository.findById(clientId);
        }

        public Optional<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }
}

