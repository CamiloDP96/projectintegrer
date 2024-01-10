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
import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.service.BillDetailService;
import com.projecti.projectintegrer.service.ReservationService;

public class ReservationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    void testRegisterReserv() throws Exception {
        // Mock request data
        ReservationDto reservationDto = new ReservationDto(/* Add necessary parameters */);

        mockMvc.perform(post("/api/v1/reservation/createReserv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reservationDto)))
                .andExpect(status().isOk());

        // Verify if reservationService.createReservation was called
        verify(reservationService, times(1)).createReservation(reservationDto);
    }

    @Test
    void testShowRooms() throws Exception {
        // Mocking behavior of reservationService.getAllReservation()
        when(reservationService.getAllReservation()).thenReturn(/* Mocked reservation list */);

        mockMvc.perform(get("/api/v1/reservation/reservationsList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Verify if reservationService.getAllReservation was called
        verify(reservationService, times(1)).getAllReservation();
    }

    @Test
    void testDeleteReserv() throws Exception {
        Integer reservIdToDelete = 1; // Replace with an actual ID

        mockMvc.perform(delete("/api/v1/reservation/deleteReserv/{id}", reservIdToDelete))
                .andExpect(status().isNoContent());

        // Verify if reservationService.deleteReservation was called with the provided ID
        verify(reservationService, times(1)).deleteReservation(reservIdToDelete);
    }

    @Test
    void testUpdateReserv() throws Exception {
        // Mock request data
        ReservationDto reservationDto = new ReservationDto(/* Add necessary parameters */);

        mockMvc.perform(put("/api/v1/reservation/update/reserv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reservationDto)))
                .andExpect(status().isNoContent());

        // Verify if reservationService.updateReservation was called
        verify(reservationService, times(1)).updateReservation(reservationDto);
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
