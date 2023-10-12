package com.honey.apiplayground.repository;


import com.honey.apiplayground.creator.UserCreator;
import com.honey.apiplayground.domain.Country;
import com.honey.apiplayground.domain.Hobby;
import com.honey.apiplayground.domain.ProgrammingTimeOption;
import com.honey.apiplayground.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
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
        final User userToSaveBeforeSearch = getUserCopyWithForeignKeyValues(UserCreator.validUser());
        final User savedUser = userRepository.save(userToSaveBeforeSearch);

        final Optional<User> retrievedUserFromDatabase = userRepository.findById(savedUser.getId());

        assertNotNull(retrievedUserFromDatabase);
        assertTrue(retrievedUserFromDatabase.isPresent());
        assertEquals(savedUser, retrievedUserFromDatabase.get());
    }

    @Test
    @DisplayName("Saving new user should be successfully inserted")
    void savingNewUserShouldBeSuccessfullyInserted() {
        final User userToSave = getUserCopyWithForeignKeyValues(UserCreator.validUser());
        final User savedUser = userRepository.save(userToSave);

        assertNotNull(savedUser);
        assertEquals(userToSave, savedUser);
    }

    @ParameterizedTest(name = "Saving new user should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewUserShouldThrowConstraintViolationException(User user) {
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(getUserCopyWithForeignKeyValues(user)));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(UserCreator.userWithNullNickNameValue()),
                Arguments.of(UserCreator.userWithNickNameTooBig())
        };
    }

    private User getUserCopyWithForeignKeyValues(User user) {
        final Country savedCountry = countryRepository.save(user.getBornData().getCountry());
        final List<Hobby> savedHobbies = hobbyRepository.saveAll(user.getHobbies());
        final ProgrammingTimeOption savedPto = programmingTimeOptionRepository.save(user.getProgrammingTimeOption());

        return UserCreator.userCopyWithForeignValues(user, savedPto, savedCountry, savedHobbies);
    }
}
