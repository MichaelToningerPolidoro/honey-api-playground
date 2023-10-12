package com.honey.apiplayground.repository;

import com.honey.apiplayground.creator.ProgrammingTimeOptionCreator;
import com.honey.apiplayground.domain.ProgrammingTimeOption;
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
public class ProgrammingTimeOptionRepositoryTest {

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    @BeforeAll
    static void beforeAll(@Autowired ProgrammingTimeOptionRepository programmingTimeOptionRepository) {
        programmingTimeOptionRepository.deleteAll();
    }

    @Test
    @DisplayName("Retrieve all values from database should return all programming time options successfully")
    void findAllShouldReturnAllProgrammingTimeOptionsSuccessfully() {
        final ProgrammingTimeOption programmingTimeOptionToSave = ProgrammingTimeOptionCreator.validProgrammingTimeOption();
        programmingTimeOptionRepository.save(programmingTimeOptionToSave);

        final List<ProgrammingTimeOption> programmingTimeOptionsRetrievedFromDatabase = programmingTimeOptionRepository.findAll();

        programmingTimeOptionsRetrievedFromDatabase.forEach(element -> System.out.println(element.getProgrammingTime()));

        assertNotNull(programmingTimeOptionsRetrievedFromDatabase);
        assertFalse(programmingTimeOptionsRetrievedFromDatabase.isEmpty());
        assertNotNull(programmingTimeOptionsRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, programmingTimeOptionsRetrievedFromDatabase.get(0).getId());
        assertEquals(programmingTimeOptionToSave.getProgrammingTime(), programmingTimeOptionsRetrievedFromDatabase.get(0).getProgrammingTime());
    }

    @Test
    @DisplayName("Saving new programming time option should be successfully inserted")
    void savingNewProgrammingTimeOptionShouldBeSuccessfullyInserted() {
        final ProgrammingTimeOption programmingTimeOptionToSave = ProgrammingTimeOptionCreator.validProgrammingTimeOption();
        final ProgrammingTimeOption savedProgrammingTimeOption = programmingTimeOptionRepository.save(programmingTimeOptionToSave);

        assertNotNull(savedProgrammingTimeOption);
        assertInstanceOf(Integer.class, savedProgrammingTimeOption.getId());
        assertEquals(programmingTimeOptionToSave.getProgrammingTime(), savedProgrammingTimeOption.getProgrammingTime());
    }

    @Test
    @DisplayName("Saving duplicated programming time option should thrown an exception")
    void savingDuplicatedProgrammingTimeOptionShouldThrownException() {
        final ProgrammingTimeOption programmingTimeOptionToSave = ProgrammingTimeOptionCreator.validProgrammingTimeOption();
        final ProgrammingTimeOption duplicatedProgrammingTime = ProgrammingTimeOptionCreator.getCopyWithDifferentId(programmingTimeOptionToSave);

        programmingTimeOptionRepository.save(programmingTimeOptionToSave);

        assertThrows(DataIntegrityViolationException.class, () -> programmingTimeOptionRepository.save(duplicatedProgrammingTime));
    }

    @ParameterizedTest(name = "Constraint violation tests")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewProgrammingTimeShouldThrowConstraintViolationException(ProgrammingTimeOption programmingTimeOption) {
        assertThrows(DataIntegrityViolationException.class, () -> programmingTimeOptionRepository.save(programmingTimeOption));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(ProgrammingTimeOptionCreator.programmingTimeOptionWithNullProgrammingTimeOptionValue()),
                Arguments.of(ProgrammingTimeOptionCreator.programmingTimeOptionWithProgrammingTimeOptionValueTooBig())
        };
    }
}
