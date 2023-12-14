package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.domain.entities.Room;
import com.projecti.projectintegrer.repositories.RoomRepository;

@Service
public record RoomService(RoomRepository roomRepository) {
     // Create
    public void createRoom(RoomDto roomDto){
        Room room = Room.builder()
            .room(roomDto.room())
            .searcheed_time(roomDto.searcheed_time())
            .status(roomDto.status())
            .build();

        roomRepository.save(room);
    }

    // Update
    public void updateRoom(RoomDto roomDto){
        Room room = roomRepository.findById(roomDto.id())
            .orElseThrow(() -> new RuntimeException("Room not found..."));
        updateRoomData(room, roomDto);
        roomRepository.save(room);
    }

    private void updateRoomData(
        Room room,
        RoomDto roomDto
        ){
            room.setId(roomDto.id());
            room.setRoom(roomDto.room());
            room.setSearcheed_time(roomDto.searcheed_time());
            room.setStatus(roomDto.status());
        }

        // Delete
        public void deleteRoom(Integer roomId){
            Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new RuntimeException("Room not found"));
            roomRepository.delete(room);
        }

        public List<Room> getAllRooms(){
            return roomRepository.findAll();
        }
        public Optional<Room> getRoomById (Integer roomId){
            return roomRepository.findById(roomId);
        }
}

