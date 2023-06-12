package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.creator.HobbyCreator;
import com.honeyautomation.apiplayground.domain.Hobby;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class HobbyRepositoryTest {

    @Autowired
    private HobbyRepository hobbyRepository;

    @BeforeAll
    static void beforeAll(@Autowired HobbyRepository hobbyRepository) {
        hobbyRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieve all values from database should return all hobbies successfully")
    void findAllShouldReturnAllHobbiesSuccessfully() {
        final Hobby hobbyToSave = HobbyCreator.validHobby();
        hobbyRepository.save(hobbyToSave);

        final List<Hobby> hobbiesRetrievedFromDatabase = hobbyRepository.findAll();

        assertNotNull(hobbiesRetrievedFromDatabase);
        assertFalse(hobbiesRetrievedFromDatabase.isEmpty());
        assertNotNull(hobbiesRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, hobbiesRetrievedFromDatabase.get(0).getId());
        assertEquals(hobbyToSave.getHobby(), hobbiesRetrievedFromDatabase.get(0).getHobby());
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

    @Test
    @DisplayName("Saving duplicated hobby should thrown an exception")
    void savingDuplicatedHobbyShouldThrownException() {
        final Hobby hobbyToSave = HobbyCreator.validHobby();
        final Hobby duplicatedHobby = HobbyCreator.getCopyWithDifferentId(hobbyToSave);

        hobbyRepository.save(hobbyToSave);

        assertThrows(DataIntegrityViolationException.class, () -> hobbyRepository.save(duplicatedHobby));
    }

    @ParameterizedTest(name = "Constraint violation tests")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewHobbyShouldThrowConstraintViolationException(Hobby hobby) {
        assertThrows(DataIntegrityViolationException.class, () -> hobbyRepository.save(hobby));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(HobbyCreator.hobbyWithNullHobbyValue()),
                Arguments.of(HobbyCreator.hobbyTooBig())
        };
    }
}