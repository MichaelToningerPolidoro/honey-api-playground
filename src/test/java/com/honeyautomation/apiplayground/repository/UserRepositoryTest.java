package com.honeyautomation.apiplayground.repository;


import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.factory.UserCreator;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final User userToSaveBeforeTests = UserCreator.validUser();

    private final Set<User> userDataSet = new HashSet<>();

    @BeforeEach
    void beforeEach() {
        userRepository.deleteAll();

        userDataSet.add(userToSaveBeforeTests);
        userRepository.saveAll(userDataSet);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all users successfully")
    void findAllShouldReturnAllUsersSuccessfully() {
        final List<User> usersRetrievedFromDatabase = userRepository.findAll();

        assertNotNull(usersRetrievedFromDatabase);
        assertFalse(usersRetrievedFromDatabase.isEmpty());
        assertTrue(usersRetrievedFromDatabase.contains(userToSaveBeforeTests));
    }

    @Test
    @DisplayName("Saving new user should be successfully inserted")
    void savingNewUserShouldBeSuccessfullyInserted() {
        final User userToSave = UserCreator.validUser();
        final User savedUser = userRepository.save(userToSave);

        assertNotNull(savedUser);
        assertEquals(userToSave, savedUser);
    }

    @ParameterizedTest(name = "Saving new user should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewUserShouldThrowConstraintViolationException(User user) {
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(user));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(userToSaveBeforeTests),
                Arguments.of(UserCreator.userWithNullNickNameValue()),
                Arguments.of(UserCreator.userWithNickNameTooBig())
        };
    }
}
