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
import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.service.BillDetailService;
import com.projecti.projectintegrer.service.RoomService;

public class RoomControllerTest {
    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    void testRegisterRoom() throws Exception {
        // Mock request data
        RoomDto roomDto = new RoomDto(/* Add necessary parameters */);

        mockMvc.perform(post("/api/v1/room/createRoom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(roomDto)))
                .andExpect(status().isOk());

        // Verify if roomService.createRoom was called
        verify(roomService, times(1)).createRoom(roomDto);
    }

    @Test
    void testShowRooms() throws Exception {
        // Mocking behavior of roomService.getAllRooms()
        when(roomService.getAllRooms()).thenReturn(/* Mocked room list */);

        mockMvc.perform(get("/api/v1/room/roomList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify if roomService.getAllRooms was called
        verify(roomService, times(1)).getAllRooms();
    }

    @Test
    void testDeleteRoom() throws Exception {
        Integer roomIdToDelete = 1; // Replace with an actual ID

        mockMvc.perform(delete("/api/v1/room/deleteRoom/{id}", roomIdToDelete))
                .andExpect(status().isNoContent());

        // Verify if roomService.deleteRoom was called with the provided ID
        verify(roomService, times(1)).deleteRoom(roomIdToDelete);
    }

    @Test
    void testUpdateRoom() throws Exception {
        // Mock request data
        RoomDto roomDto = new RoomDto(/* Add necessary parameters */);

        mockMvc.perform(put("/api/v1/room/update/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(roomDto)))
                .andExpect(status().isNoContent());

        // Verify if roomService.updateRoom was called
        verify(roomService, times(1)).updateRoom(roomDto);
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
