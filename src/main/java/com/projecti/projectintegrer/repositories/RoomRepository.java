package com.projecti.projectintegrer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecti.projectintegrer.domain.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{
}
