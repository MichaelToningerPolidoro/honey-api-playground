package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.factory.ProgrammingTimeOptionFactory;
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
public class ProgrammingTimeOptionRepositoryTest {

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;
    private Set<ProgrammingTimeOption> programmingTimeOptionDataSet;
    private final ProgrammingTimeOption programmingTimeOptionData = ProgrammingTimeOptionFactory.validProgrammingTimeOption();

    @BeforeEach
    void before() {
        programmingTimeOptionRepository.deleteAll();

        programmingTimeOptionDataSet = new HashSet<>();
        programmingTimeOptionDataSet.add(programmingTimeOptionData);

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
        assertEquals(programmingTimeOptionData.getProgrammingTime(), programmingTimeOptionsRetrievedFromDatabase.get(0).getProgrammingTime());
    }
}
