package com.projecti.projectintegrer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.assertj.core.api.Assertions;


@ActiveProfiles("test")
@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    void testCreateRoom() throws Exception {
        RoomDto roomDto = new RoomDto(null, 101, "wifi", "standar", 100.00);

        this.mockMvc.perform(post("/api/v1/room/createRoom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(roomDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(roomService).createRoom(roomDto);
    }

    @Test
    void testGetAllRooms() throws Exception {
        final Integer offset = 0;
        final Integer limit = 10;

        RoomDto room101Dto = new RoomDto(101, 101, "Wifi", "standar", 100.00);
        RoomDto room102Dto = new RoomDto(102, 102, "wifi and minibar", "double", 250.00);

        List<RoomDto> roomsDto = Arrays.asList(room101Dto, room102Dto);

        when(roomService.getAllRooms(offset, limit)).thenReturn(roomsDto);
        MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/room/roomList/" + offset + "/" + limit))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        verify(roomService).getAllRooms(offset, limit);
        Assertions.assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(roomsDto));
    }

    @Test
    void testGetRoomById() throws Exception {
        final Integer id = 100;

        RoomDto room101Dto = new RoomDto(100, 101, "wifi", "standar", 100.00);

        when(roomService.getRoomById(id)).thenReturn(room101Dto);
        mockMvc.perform(get("/api/v1/room/" + id))
                .andExpect(status().isFound())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(room101Dto)));
    }

    @Test
    void testGetRoomByIdNotFound() throws Exception {
        final Integer id = 100;

        when(roomService.getRoomById(id)).thenThrow(new ReservException(MessageEnum.DATA_NOT_FOUND));

        mockMvc.perform(get("/api/v1/room/" + id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ReservException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals(MessageEnum.DATA_NOT_FOUND.getMessage(), result.getResolvedException().getMessage()));
    }
}

