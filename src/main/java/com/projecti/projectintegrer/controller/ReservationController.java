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

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.service.ReservationService;

@RestController
@RequestMapping("api/v1/reservation")
public record ReservationController(
    ReservationService reservationService
) {
    @PostMapping("/createReserv")
    public ResponseEntity<?> registerReserv(@RequestBody ReservationDto reservationDto){
        reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("reservationsList")
    public ResponseEntity<?> showRooms(){
        return new ResponseEntity<>(reservationService.getAllReservation(),HttpStatus.OK);
    }

    @DeleteMapping("deleteReserv/{id}")
    public ResponseEntity<?> deleteReserv(@PathVariable("id") Integer id){
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/reserv")
        public ResponseEntity<?> updateReserv(@RequestBody ReservationDto reservationDto){
            reservationService.updateReservation(reservationDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
