package com.projecti.projectintegrer.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.domain.entities.Room;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.RoomMapper;
import com.projecti.projectintegrer.repositories.RoomRepository;



@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomMapper mapper;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    void testCreateRoom() {
        RoomDto roomDto = new RoomDto(null, 101, "wifi", "standar", 100.00);
        Room room = Room.builder()
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();

            when(mapper.toEntity(roomDto)).thenReturn(room);
            roomService.createRoom(roomDto);

            verify(roomRepository).save(room);
    }

    @Test
    @SneakyThrows
    void testGetAllRooms() {
        final Integer offset = 0;
        final Integer limit = 100;
        Room room101 = Room.builder()
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();
        Room room102 = Room.builder()
            .room(102)
            .benefits("wifi n minibar")
            .type("double")
            .pricePerNigth(250.00)
            .build();
        List<Room> rooms = Arrays.asList(room101, room102);

        List<RoomDto> expecteDtosList = Arrays.asList(mapper.toDto(room101), mapper.toDto(room102));

        when(roomRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(rooms));
        when(mapper.toDtoList(rooms)).thenReturn(expecteDtosList);

        List<RoomDto> result = roomService.getAllRooms(offset, limit);

        assertEquals(expecteDtosList, result);
    }

    @Test
    void testGetAllRoomsNotReturnData() {
        final Integer offset = 0;
        final Integer limit = 0;

        when(roomRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(new ArrayList<>()));

        assertThrows(ReservException.class, () -> roomService.getAllRooms(offset, limit));
    }

    @Test
    @SneakyThrows
    void testGetRoomById() {
        final Integer id = 100;

        RoomDto expectedDto = new RoomDto(100, 101, "wifi", "standar", 100.00);

        Room room = Room.builder()
            .id(100)
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();

            when(mapper.toDto(room)).thenReturn(expectedDto);
            when(roomRepository.findById(id)).thenReturn(Optional.of(room));
            RoomDto roomResult = roomService.getRoomById(id);

            assertEquals(expectedDto, roomResult);
    }

    @Test
    void testGetRoomByIdNotReturnData() {
        final Integer id = 100;

        when(roomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReservException.class,() -> roomService.getRoomById(id));
    }

    @Test
    @SneakyThrows
    void testUpdateRoom() {
        final Integer id = 100;

        RoomDto roomDto = new RoomDto(100, 105, "wifi", "standar",100.00);

        Room roomFound = Room.builder()
            .id(100)
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();

        Room room = Room.builder()
            .id(100)
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();

        when(mapper.toEntity(roomDto)).thenReturn(room);
        when(roomRepository.findById(id)).thenReturn(Optional.of(roomFound));
        roomService.updateRoom(id, roomDto);
    }

    @Test
    void testUpdateRoomNotReturnData() {
        final Integer id = 100;
        RoomDto roomDto = new RoomDto(100, 101, "wifi", "standar", 100.00);

        when(roomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReservException.class,() -> roomService.updateRoom(id, roomDto));
    }

    @Test
    @SneakyThrows
    void testDeleteRoom() {
        final Integer id = 100;

        Room roomFound = Room.builder()
            .id(100)
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00)
            .build();

            when(roomRepository.findById(id)).thenReturn(Optional.of(roomFound));
            roomService.deleteRoom(id);

            verify(roomRepository).delete(roomFound);
    }

    @Test
    void testDeleteRoomNotReturnData() {
        final Integer id = 100;

        when(roomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ReservException.class,() -> roomService.deleteRoom(id));
    }
}
