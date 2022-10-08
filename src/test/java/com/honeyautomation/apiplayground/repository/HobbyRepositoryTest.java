package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@DisplayName("Hobby Repository tests")
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
    @DisplayName("Retrieve all values from database should return all values successfully")
    void findAllShouldReturnAllValuesSuccessfully() {
        final List<Hobby> hobbies = hobbyRepository.findAll();

        Assertions.assertNotNull(hobbies);
        Assertions.assertFalse(hobbies.isEmpty());
        Assertions.assertEquals(hobbies.size(), hobbiesData.size());
        Assertions.assertNotNull(hobbies.get(0));
        Assertions.assertEquals(hobbies.get(0).getHobby(), "Programming");
    }
}