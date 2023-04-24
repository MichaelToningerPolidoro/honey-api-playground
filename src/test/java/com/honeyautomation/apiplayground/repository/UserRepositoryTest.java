package com.honeyautomation.apiplayground.repository;


import com.honeyautomation.apiplayground.domain.Country;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.factory.CountryCreator;
import com.honeyautomation.apiplayground.factory.HobbyCreator;
import com.honeyautomation.apiplayground.factory.ProgrammingTimeOptionCreator;
import com.honeyautomation.apiplayground.factory.UserCreator;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    private static final User userToSaveBeforeTests = UserCreator.validUser();
    private static final Country countryToSaveBeforeTests = CountryCreator.validCountry();
    private static final Hobby hobbyToSaveBeforeTests = HobbyCreator.validHobby();
    private static final ProgrammingTimeOption programmingTimeOptionToSaveBeforeTests =
            ProgrammingTimeOptionCreator.validProgrammingTimeOption();

    private final Set<User> userDataSet = new HashSet<>();

    @BeforeEach
    void beforeEach() {
        countryRepository.deleteAll();
        hobbyRepository.deleteAll();
        programmingTimeOptionRepository.deleteAll();

        countryRepository.save(countryToSaveBeforeTests);
        hobbyRepository.save(hobbyToSaveBeforeTests);
        programmingTimeOptionRepository.save(programmingTimeOptionToSaveBeforeTests);

        userDataSet.add(userToSaveBeforeTests);
        userRepository.saveAll(userDataSet);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all users successfully")
    void findAllShouldReturnAllUsersSuccessfully() {
        final List<User> usersRetrievedFromDatabase = userRepository.findAll();

        Assertions.assertNotNull(usersRetrievedFromDatabase);
        Assertions.assertFalse(usersRetrievedFromDatabase.isEmpty());
        Assertions.assertTrue(usersRetrievedFromDatabase.contains(userToSaveBeforeTests));
    }
}
