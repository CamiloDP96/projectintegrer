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

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.domain.entities.Room;
import com.projecti.projectintegrer.repositories.RoomRepository;

public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void testCreateRoom() {
        RoomDto roomDto = new RoomDto(/* Add necessary parameters */);
        Room room = Room.builder()
                .room(roomDto.room())
                .searcheed_time(roomDto.searcheed_time())
                .status(roomDto.status())
                .build();

        roomService.createRoom(roomDto);

        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testUpdateRoom() {
        RoomDto roomDto = new RoomDto(/* Add necessary parameters */);
        Room room = Room.builder()
                .id(roomDto.id())
                .room(roomDto.room())
                .searcheed_time(roomDto.searcheed_time())
                .status(roomDto.status())
                .build();

        when(roomRepository.findById(roomDto.id())).thenReturn(Optional.of(room));

        roomService.updateRoom(roomDto);

        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void testDeleteRoom() {
        Integer roomId = 1; // Replace with an actual ID
        Room room = Room.builder().build();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        roomService.deleteRoom(roomId);

        verify(roomRepository, times(1)).delete(room);
    }

    @Test
    void testGetAllRooms() {
        List<Room> roomList = new ArrayList<>(); // Replace with a list of mock data

        when(roomRepository.findAll()).thenReturn(roomList);

        List<Room> result = roomService.getAllRooms();

        assertEquals(roomList.size(), result.size());
    }

    @Test
    void testGetRoomById() {
        Integer roomId = 1; // Replace with an actual ID
        Room room = Room.builder().build();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        Optional<Room> result = roomService.getRoomById(roomId);

        assertTrue(result.isPresent());
        assertEquals(room, result.get());
    }
}
