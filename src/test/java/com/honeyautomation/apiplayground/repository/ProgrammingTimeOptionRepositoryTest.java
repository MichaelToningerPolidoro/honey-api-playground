package com.honeyautomation.apiplayground.repository;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
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
    private Set<ProgrammingTimeOption> programmingTimeOptionData;

    @BeforeEach
    void before() {
        programmingTimeOptionRepository.deleteAll();

        programmingTimeOptionData = new HashSet<>();
        programmingTimeOptionData.add(new ProgrammingTimeOption(1, "Test time option"));

        programmingTimeOptionRepository.saveAll(programmingTimeOptionData);
    }

    @Test
    @DisplayName("Retrieve all values from database should return all programming time options successfully")
    void findAllShouldReturnAllProgrammingTimeOptionsSuccessfully() {
        final List<ProgrammingTimeOption> programmingTimeOptions = programmingTimeOptionRepository.findAll();

        assertNotNull(programmingTimeOptions);
        assertFalse(programmingTimeOptions.isEmpty());
        assertEquals(programmingTimeOptions.size(), programmingTimeOptionData.size());
        assertNotNull(programmingTimeOptions.get(0));
        assertInstanceOf(Integer.class, programmingTimeOptions.get(0).getId());
        assertEquals(programmingTimeOptions.get(0).getProgrammingTime(), "Test time option");
    }
}
