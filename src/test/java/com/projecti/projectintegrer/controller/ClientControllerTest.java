package com.projecti.projectintegrer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecti.projectintegrer.domain.dto.BillDetailDto;
import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.service.BillDetailService;
import com.projecti.projectintegrer.service.ClientService;

public class ClientControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testRegisterClient() throws Exception {
        // Mock request data
        ClientDto clientDto = new ClientDto(/* Add necessary parameters */);

        mockMvc.perform(post("/api/v1/client/createClient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto)))
                .andExpect(status().isOk());

        // Verify if clientService.createClient was called
        verify(clientService, times(1)).createClient(clientDto);
    }

    @Test
    void testShowClients() throws Exception {
        // Mocking behavior of clientService.getAllClients()
        when(clientService.getAllClients()).thenReturn(/* Mocked client list */);

        mockMvc.perform(get("/api/v1/client/clientList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify if clientService.getAllClients was called
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testDeleteClient() throws Exception {
        Integer clientIdToDelete = 1; // Replace with an actual ID

        mockMvc.perform(delete("/api/v1/client/delete/{id}", clientIdToDelete))
                .andExpect(status().isNoContent());

        // Verify if clientService.deleteClient was called with the provided ID
        verify(clientService, times(1)).deleteClient(clientIdToDelete);
    }

    @Test
    void testUpdateClient() throws Exception {
        // Mock request data
        ClientDto clientDto = new ClientDto(/* Add necessary parameters */);

        mockMvc.perform(put("/api/v1/client/update/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDto)))
                .andExpect(status().isNoContent());

        // Verify if clientService.updateClient was called
        verify(clientService, times(1)).updateClient(clientDto);
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
