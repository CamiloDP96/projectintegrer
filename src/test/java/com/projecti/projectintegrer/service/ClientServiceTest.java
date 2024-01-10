package com.projecti.projectintegrer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.repositories.ClientRepository;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testCreateClient() {
        ClientDto clientDto = new ClientDto(/* Add necessary parameters */);
        Client client = Client.builder()
                .name(clientDto.name())
                .build();

        clientService.createClient(clientDto);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        ClientDto clientDto = new ClientDto(/* Add necessary parameters */);
        Client client = Client.builder()
                .id(clientDto.id())
                .name(clientDto.name())
                .build();

        when(clientRepository.findById(clientDto.id())).thenReturn(Optional.of(client));

        clientService.updateClient(clientDto);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testDeleteClient() {
        Integer clientId = 1; // Replace with an actual ID
        Client client = Client.builder().build();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        clientService.deleteClient(clientId);

        verify(clientRepository, times(1)).delete(client);
    }

    @Test
    void testGetAllClients() {
        List<Client> clientList = new ArrayList<>(); // Replace with a list of mock data

        when(clientRepository.findAll()).thenReturn(clientList);

        List<Client> result = clientService.getAllClients();

        assertEquals(clientList.size(), result.size());
    }

    @Test
    void testGetClientById() {
        Integer clientId = 1; // Replace with an actual ID
        Client client = Client.builder().build();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(clientId);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    void testFindByName() {
        String name = "John"; // Replace with an existing name
        Client client = Client.builder().name(name).build();

        when(clientRepository.findByName(name)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }
}
