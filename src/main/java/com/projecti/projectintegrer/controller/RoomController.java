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

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.RoomService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/room")
public record RoomController(
    RoomService roomService
) {
    @PostMapping("/createRoom")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerRoom(@RequestBody RoomDto roomDto){
        roomService.createRoom(roomDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("roomList/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> showRooms(
        @PathVariable("offset") Integer offset,
        @PathVariable("limit") Integer limit
    )   throws ReservException {
        List<RoomDto> rooms = roomService.getAllRooms(offset,limit);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> findRoomById(@PathVariable("id") Integer id) throws ReservException {
        RoomDto rooms = roomService.getRoomById(id);
        return new ResponseEntity<>(rooms, HttpStatus.FOUND);
    }

    @DeleteMapping("deleteRoom/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Integer id) throws ReservException {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/room")
    @SecurityRequirement(name = "bearerAuth")
        public ResponseEntity<?> updateRoom(@PathVariable("id") Integer id, @RequestBody RoomDto roomDto) throws ReservException{
            roomService.updateRoom(id, roomDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
