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

import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.service.RoomService;

@RestController
@RequestMapping("api/v1/room")
public record RoomController(
    RoomService roomService
) {
    @PostMapping("/createRoom")
    public ResponseEntity<?> registerRoom(@RequestBody RoomDto roomDto){
        roomService.createRoom(roomDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("roomList")
    public ResponseEntity<?> showRooms(){
        return new ResponseEntity<>(roomService.getAllRooms(),HttpStatus.OK);
    }

    @DeleteMapping("deleteRoom/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Integer id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("update/room")
        public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
            roomService.updateRoom(roomDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
