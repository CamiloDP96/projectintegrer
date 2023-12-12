package com.projecti.projectintegrer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecti.projectintegrer.domain.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
}
