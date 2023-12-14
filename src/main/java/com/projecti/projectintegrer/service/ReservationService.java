package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.domain.entities.Reservation;
import com.projecti.projectintegrer.repositories.ReservationRepository;

@Service
public record ReservationService(ReservationRepository reservationRepository) {
     // Create
    public void createReservation(ReservationDto reservationDto){
        Reservation reservation = Reservation.builder()
            .quantityPeople(reservationDto.quantityPeople())
            .checkIn(reservationDto.checkIn())
            .checkOut(reservationDto.checkOut())
            .status(reservationDto.status())
            .build();

        reservationRepository.save(reservation);
    }

    // Update
    public void updateReservation(ReservationDto reservationDto){
        Reservation reservation = reservationRepository.findById(reservationDto.id())
            .orElseThrow(() -> new RuntimeException("Reservation not found..."));
        updateReservationData(reservation, reservationDto);
        reservationRepository.save(reservation);
    }

    private void updateReservationData(
        Reservation reservation,
        ReservationDto reservationDto
        ){
            reservation.setId(reservationDto.id());
            reservation.setQuantityPeople(reservationDto.quantityPeople());
            reservation.setCheckIn(reservationDto.checkIn());
            reservation.setCheckOut(reservationDto.checkOut());
            reservation.setStatus(reservationDto.status());
        }

        // Delete
        public void deleteReservation(Integer reservationId){
            Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new RuntimeException("Reservation not found"));
            reservationRepository.delete(reservation);
        }

        public List<Reservation> getAllReservation(){
            return reservationRepository.findAll();
        }
        public Optional<Reservation> getReservationById (Integer reservationId){
            return reservationRepository.findById(reservationId);
        }
}

