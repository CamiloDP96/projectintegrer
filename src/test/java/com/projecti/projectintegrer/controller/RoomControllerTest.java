package com.projecti.projectintegrer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projecti.projectintegrer.domain.dto.RoomDto;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.exception.ReservExceptionHandler;
import com.projecti.projectintegrer.service.RoomService;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
@WithMockUser
@SpringBootTest
public class RoomControllerTest {

    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(roomController)
            .setControllerAdvice(new ReservExceptionHandler())
            .build();
    }

    @Test
    void testCreateRoom() throws Exception {
        RoomDto roomDto = new RoomDto(null, 101, "wifi", "standar", 100.00);

        this.mockMvc.perform(post("/api/v1/room")
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

        RoomDto room101Dto = new RoomDto(101,101 , "Wifi", "standar", 100.00);
        RoomDto room102Dto = new RoomDto(102, 102, "wifi and minibar", "double", 250.00);

        List<RoomDto> roomsDto = Arrays.asList(room101Dto,room102Dto);

        when(roomService.getAllRooms(offset, limit)).thenReturn(roomsDto);
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/room/roomList/" + offset + "/" + limit))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

            verify(roomService).getAllRooms(offset, limit);
            assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(roomsDto));
    }

    @Test
    @SneakyThrows
    void testGetRoomById() {
        final Integer id = 100;

        RoomDto room101Dto = new RoomDto(100, 101, "wifi", "standar", 100.00);

        when(roomService.getRoomById(id)).thenReturn(room101Dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/room/" + id))
            .andExpect(status().isFound())
            .andReturn()
            .getResponse();

            verify(roomService).getRoomById(id);
            assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(room101Dto));
    }

    @Test
    void testGetRoomByIdNotFound() throws Exception{
        final Integer id = 100;

        when(roomService.getRoomById(id)).thenThrow(new ReservException(MessageEnum.DATA_NOT_FOUND));

        RoomController mockedRoomController = mock(RoomController.class);

        doThrow(new ReservException(MessageEnum.DATA_NOT_FOUND))
            .when(mockedRoomController).findRoomById(id);

        mockMvc.perform(get("/api/v1/room/" + id))
            .andExpect(status().isNotFound())
            .andExpect(result -> assertInstanceOf(ReservException.class, result.getResolvedException()))
            .andExpect(result -> assertEquals(MessageEnum.DATA_NOT_FOUND.getMessage(), Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}
