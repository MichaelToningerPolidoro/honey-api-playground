package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.factory.HobbyFactory;
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
    private Set<Hobby> hobbiesDataSet;
    private final Hobby hobbyData = HobbyFactory.validHobby();

    @BeforeEach
    void before() {
        hobbyRepository.deleteAll();

        hobbiesDataSet = new HashSet<>();
        hobbiesDataSet.add(hobbyData);

        hobbyRepository.saveAll(hobbiesDataSet);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all hobbies successfully")
    void findAllShouldReturnAllHobbiesSuccessfully() {
        final List<Hobby> hobbiesRetrievedFromDatabase = hobbyRepository.findAll();

        assertNotNull(hobbiesRetrievedFromDatabase);
        assertFalse(hobbiesRetrievedFromDatabase.isEmpty());
        assertEquals(hobbiesDataSet.size(), hobbiesRetrievedFromDatabase.size());
        assertNotNull(hobbiesRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, hobbiesRetrievedFromDatabase.get(0).getId());
        assertEquals(hobbyData.getHobby(), hobbiesRetrievedFromDatabase.get(0).getHobby());
    }
}