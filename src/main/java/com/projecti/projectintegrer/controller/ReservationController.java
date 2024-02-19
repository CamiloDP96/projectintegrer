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

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.ReservationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/reservation")
public record ReservationController(
    ReservationService reservationService
) {
    @PostMapping("/createReserv")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerReserv(@RequestBody ReservationDto reservationDto){
        reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("reservationsList/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> showReservs(
        @PathVariable("offset") Integer offset,
        @PathVariable("limit") Integer limit) throws ReservException {
            List<ReservationDto> reservations = reservationService.getAllReservation(offset, limit);
        return new ResponseEntity<>(reservations,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findReservationById(@PathVariable("id") Integer id) throws ReservException {
        ReservationDto reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.FOUND);
    }

    @DeleteMapping("deleteReserv/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteReserv(@PathVariable("id") Integer id) throws ReservException {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateReserv/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateReserv(@PathVariable("id") Integer id, @RequestBody ReservationDto reservationDto) throws ReservException{
        reservationService.updateReservation(id, reservationDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
