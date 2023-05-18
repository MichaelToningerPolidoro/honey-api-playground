package com.honeyautomation.apiplayground.repository;


import com.honeyautomation.apiplayground.creator.UserCreator;
import com.honeyautomation.apiplayground.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    @Test
    @DisplayName("Find should return user successfully")
    void findShouldReturnUserSuccessfully() {
        final User userToSaveBeforeSearch = UserCreator.validUser();
        insertForeignValuesToUser(userToSaveBeforeSearch);
        final User savedUser = userRepository.save(userToSaveBeforeSearch);

        final Optional<User> retrievedUserFromDatabase = userRepository.findById(savedUser.getId());

        assertNotNull(retrievedUserFromDatabase);
        assertTrue(retrievedUserFromDatabase.isPresent());
        assertEquals(savedUser.getName(), retrievedUserFromDatabase.get().getName());
    }

    @Test
    @DisplayName("Saving new user should be successfully inserted")
    void savingNewUserShouldBeSuccessfullyInserted() {
        final User userToSave = UserCreator.validUser();
        insertForeignValuesToUser(userToSave);

        final User savedUser = userRepository.save(userToSave);

        assertNotNull(savedUser);
        assertEquals(userToSave, savedUser);
    }

    @ParameterizedTest(name = "Saving new user should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewUserShouldThrowConstraintViolationException(User user) {
        insertForeignValuesToUser(user);
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(UserCreator.userWithNullNickNameValue()),
                Arguments.of(UserCreator.userWithNickNameTooBig())
        };
    }

    private void insertForeignValuesToUser(User user) {
        countryRepository.save(user.getBornData().getCountry());
        hobbyRepository.saveAll(user.getHobbies());
        programmingTimeOptionRepository.save(user.getProgrammingTimeOption());
    }
}
