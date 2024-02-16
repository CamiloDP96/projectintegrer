package com.projecti.projectintegrer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.domain.entities.Room;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.RoomMapper;
import com.projecti.projectintegrer.repositories.RoomRepository;

@Service
public record RoomService(
    RoomRepository roomRepository,
    RoomMapper mapper
) {
     // Create
    public void createRoom(RoomDto roomDto){
        Room room = mapper.toEntity(roomDto);
        roomRepository.save(room);
    }

    // Update
    public void updateRoom(Integer Id,RoomDto roomDto) throws ReservException {
        roomRepository.findById(Id)
            .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
        Room room = mapper.toEntity(roomDto);
        roomRepository.save(room);
    }

        // Delete
        public void deleteRoom(Integer Id) throws ReservException {
            Room room = roomRepository.findById(Id)
                .orElseThrow(()-> new ReservException(MessageEnum.DATA_NOT_FOUND));
            roomRepository.delete(room);
        }

        //Getters
        public List<RoomDto> getAllRooms(Integer offset, Integer limit) throws ReservException {
            Pageable pageable = PageRequest.of(offset, limit);
            Page<Room> rooms = roomRepository.findAll(pageable);
            if (rooms.getContent().isEmpty()) {
                throw new ReservException(MessageEnum.DATA_NOT_FOUND);
            }
            return mapper.toDtoList(rooms.getContent());
        }

        public RoomDto getRoomById (Integer Id) throws ReservException {
            Room room = roomRepository.findById(Id)
                .orElseThrow(() -> new ReservException(MessageEnum.DATA_NOT_FOUND));
            return mapper.toDto(room);
        }
}

