package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.factory.HobbyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HobbyRepositoryTest {

    @Autowired
    private HobbyRepository hobbyRepository;
    private final Set<Hobby> hobbiesDataSet = new HashSet<>();
    private static final Hobby hobbyDataToSaveBeforeTests = HobbyCreator.validHobby();

    @BeforeEach
    void before() {
        hobbyRepository.deleteAll();

        hobbiesDataSet.add(hobbyDataToSaveBeforeTests);

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
        assertEquals(hobbyDataToSaveBeforeTests.getHobby(), hobbiesRetrievedFromDatabase.get(0).getHobby());
    }

    @Test
    @DisplayName("Saving new hobby should be successfully inserted")
    void savingNewValidHobbyShouldBeSuccessfullyInserted() {
        final Hobby hobbyToSave = HobbyCreator.validHobby();
        final Hobby savedHobby = hobbyRepository.save(hobbyToSave);

        assertNotNull(savedHobby);
        assertInstanceOf(Integer.class, savedHobby.getId());
        assertEquals(hobbyToSave.getHobby(), savedHobby.getHobby());
    }

    @ParameterizedTest(name = "Saving new hobby should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewHobbyShouldThrowConstraintViolationException(Hobby hobby) {
        assertThrows(DataIntegrityViolationException.class, () -> hobbyRepository.save(hobby));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(hobbyDataToSaveBeforeTests),
                Arguments.of(HobbyCreator.hobbyWithNullHobbyValue()),
                Arguments.of(HobbyCreator.hobbyTooBig())
        };
    }
}