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

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.domain.entities.Reservation;
import com.projecti.projectintegrer.repositories.ReservationRepository;

public class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void testCreateReservation() {
        ReservationDto reservationDto = new ReservationDto(/* Add necessary parameters */);
        Reservation reservation = Reservation.builder()
                .quantityPeople(reservationDto.quantityPeople())
                .checkIn(reservationDto.checkIn())
                .checkOut(reservationDto.checkOut())
                .status(reservationDto.status())
                .build();

        reservationService.createReservation(reservationDto);

        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservation() {
        ReservationDto reservationDto = new ReservationDto(/* Add necessary parameters */);
        Reservation reservation = Reservation.builder()
                .id(reservationDto.id())
                .quantityPeople(reservationDto.quantityPeople())
                .checkIn(reservationDto.checkIn())
                .checkOut(reservationDto.checkOut())
                .status(reservationDto.status())
                .build();

        when(reservationRepository.findById(reservationDto.id())).thenReturn(Optional.of(reservation));

        reservationService.updateReservation(reservationDto);

        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testDeleteReservation() {
        Integer reservationId = 1; // Replace with an actual ID
        Reservation reservation = Reservation.builder().build();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        reservationService.deleteReservation(reservationId);

        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testGetAllReservation() {
        List<Reservation> reservationList = new ArrayList<>(); // Replace with a list of mock data

        when(reservationRepository.findAll()).thenReturn(reservationList);

        List<Reservation> result = reservationService.getAllReservation();

        assertEquals(reservationList.size(), result.size());
    }

    @Test
    void testGetReservationById() {
        Integer reservationId = 1; // Replace with an actual ID
        Reservation reservation = Reservation.builder().build();

        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        Optional<Reservation> result = reservationService.getReservationById(reservationId);

        assertTrue(result.isPresent());
        assertEquals(reservation, result.get());
    }
}
