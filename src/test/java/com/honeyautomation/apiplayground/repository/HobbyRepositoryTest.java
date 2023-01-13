package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HobbyRepositoryTest {

    @Autowired
    private HobbyRepository hobbyRepository;
    private Set<Hobby> hobbiesData;

    @BeforeEach
    void before() {
        hobbyRepository.deleteAll();

        hobbiesData = new HashSet<>();
        hobbiesData.add(new Hobby(1, "Programming"));

        hobbyRepository.saveAll(hobbiesData);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all hobbies successfully")
    void findAllShouldReturnAllHobbiesSuccessfully() {
        final List<Hobby> hobbies = hobbyRepository.findAll();

        assertNotNull(hobbies);
        assertFalse(hobbies.isEmpty());
        assertEquals(hobbies.size(), hobbiesData.size());
        assertNotNull(hobbies.get(0));
        assertInstanceOf(Integer.class, hobbies.get(0).getId());
        assertEquals(hobbies.get(0).getHobby(), "Programming");
    }
}