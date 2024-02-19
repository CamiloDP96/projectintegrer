package com.projecti.projectintegrer.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.projecti.projectintegrer.domain.entities.Room;
import com.projecti.projectintegrer.repositories.RoomRepository;

import lombok.val;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveRoom() {
        Room room = Room.builder()
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00).build();

            Room savedRoom = roomRepository.save(room);

            assertNotNull(savedRoom.getId());
    }

    @Test
    public void testGetAllRooms(){
        final int offset = 0;
        final int limit = 2;
        Room room101 = Room.builder()
            .room(101)
            .benefits("wifi")
            .type("standar")
            .pricePerNigth(100.00).build();
        Room room102 = Room.builder()
            .room(102)
            .benefits("wifi n minibar")
            .type("double")
            .pricePerNigth(250.00).build();

        entityManager.persist(room101);
        entityManager.persist(room102);

        Pageable pageable = PageRequest.of(offset, limit);
        Page<Room> page = roomRepository.findAll(pageable);

        assertThat(page.getContent().size()).isEqualTo(2);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalElements()).isEqualTo(2);
    }
}
