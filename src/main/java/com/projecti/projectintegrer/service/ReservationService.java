package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.PageRanges;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.ReservationDto;
import com.projecti.projectintegrer.domain.entities.Reservation;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.ReservationMapper;
import com.projecti.projectintegrer.repositories.ReservationRepository;

@Service
public record ReservationService(
    ReservationRepository reservationRepository,
    ReservationMapper mapper
    ) {
     // Create
    public void createReservation(ReservationDto reservationDto){
        Reservation reservation = mapper.toEntity(reservationDto);
        reservationRepository.save(reservation);
    }

    // Update
    public void updateReservation(Integer Id,ReservationDto reservationDto) throws ReservException {
        reservationRepository.findById(Id)
            .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
        Reservation reservation = mapper.toEntity(reservationDto);
        reservationRepository.save(reservation);
    }

        // Delete
        public void deleteReservation(Integer Id) throws ReservException {
            Reservation reservation = reservationRepository.findById(Id)
                .orElseThrow(()-> new ReservException(MessageEnum.DATA_NOT_FOUND));
            reservationRepository.delete(reservation);
        }

        //Getters
        public List<ReservationDto> getAllReservation(Integer offset, Integer limit) throws ReservException {
            Pageable pageable = PageRequest.of(offset, limit);
            Page<Reservation> reservations = reservationRepository.findAll(pageable);
            if (reservations.getContent().isEmpty()) {
                throw new ReservException(MessageEnum.DATA_NOT_FOUND);
            }
            return mapper.toDtoList(reservations.getContent());
        }

        public ReservationDto getReservationById (Integer Id) throws ReservException {
            Reservation reservation = reservationRepository.findById(Id)
                .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
            return mapper.toDto(reservation);
        }
}

