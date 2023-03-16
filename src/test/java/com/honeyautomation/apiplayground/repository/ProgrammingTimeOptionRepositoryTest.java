package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.factory.ProgrammingTimeOptionFactory;
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
public class ProgrammingTimeOptionRepositoryTest {

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;
    private Set<ProgrammingTimeOption> programmingTimeOptionDataSet;
    private static final ProgrammingTimeOption programmingTimeOptionDataToSaveBeforeTests = ProgrammingTimeOptionFactory.validProgrammingTimeOption();

    @BeforeEach
    void before() {
        programmingTimeOptionRepository.deleteAll();

        programmingTimeOptionDataSet = new HashSet<>();
        programmingTimeOptionDataSet.add(programmingTimeOptionDataToSaveBeforeTests);

        programmingTimeOptionRepository.saveAll(programmingTimeOptionDataSet);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all programming time options successfully")
    void findAllShouldReturnAllProgrammingTimeOptionsSuccessfully() {
        final List<ProgrammingTimeOption> programmingTimeOptionsRetrievedFromDatabase = programmingTimeOptionRepository.findAll();

        assertNotNull(programmingTimeOptionsRetrievedFromDatabase);
        assertFalse(programmingTimeOptionsRetrievedFromDatabase.isEmpty());
        assertEquals(programmingTimeOptionDataSet.size(), programmingTimeOptionsRetrievedFromDatabase.size());
        assertNotNull(programmingTimeOptionsRetrievedFromDatabase.get(0));
        assertInstanceOf(Integer.class, programmingTimeOptionsRetrievedFromDatabase.get(0).getId());
        assertEquals(programmingTimeOptionDataToSaveBeforeTests.getProgrammingTime(), programmingTimeOptionsRetrievedFromDatabase.get(0).getProgrammingTime());
    }

    @Test
    @DisplayName("Saving new programming time option should be successfully inserted")
    void savingNewProgrammingTimeOptionShouldBeSuccessfullyInserted() {
        final ProgrammingTimeOption programmingTimeOptionToSave = ProgrammingTimeOptionFactory.validProgrammingTimeOption();
        final ProgrammingTimeOption programmingTimeOptionFactorySaved = programmingTimeOptionRepository.save(programmingTimeOptionToSave);

        assertNotNull(programmingTimeOptionFactorySaved);
        assertInstanceOf(Integer.class, programmingTimeOptionFactorySaved.getId());
        assertEquals(programmingTimeOptionToSave.getProgrammingTime(), programmingTimeOptionFactorySaved.getProgrammingTime());
    }

    @ParameterizedTest(name = "Saving new programming time option should thrown constraint violation")
    @MethodSource("constraintViolationProvidedParameters")
    void savingNewProgrammingTimeShouldThrowConstraintViolationException(ProgrammingTimeOption programmingTimeOption) {
        assertThrows(DataIntegrityViolationException.class, () -> programmingTimeOptionRepository.save(programmingTimeOption));
    }

    private static Arguments[] constraintViolationProvidedParameters() {
        return new Arguments[] {
                Arguments.of(programmingTimeOptionDataToSaveBeforeTests),
                Arguments.of(ProgrammingTimeOptionFactory.programmingTimeOptionWithNullProgrammingTimeOptionValue()),
                Arguments.of(ProgrammingTimeOptionFactory.programmingTimeOptionWithProgrammingTimeOptionValueTooBig())
        };
    }
}
