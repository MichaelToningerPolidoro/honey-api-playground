package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.ProgrammingTimeOptionFactory;
import com.honeyautomation.apiplayground.repository.ProgrammingTimeOptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProgrammingTimeOptionServiceTest {

    @InjectMocks
    private ProgrammingTimeOptionService programmingTimeOptionService;
    @Mock
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    @Test
    @DisplayName("Find all programming time options should return a list successfully")
    void findAllProgrammingTimeOptionsShouldReturnAList() {
        final ProgrammingTimeOption programmingTimeOptionMockData = ProgrammingTimeOptionFactory.validProgrammingTimeOption();
        final List<ProgrammingTimeOption> programmingTimeOptionListMockData = List.of(programmingTimeOptionMockData);

        when(programmingTimeOptionRepository.findAll()).thenReturn(programmingTimeOptionListMockData);

        assertDoesNotThrow(() -> programmingTimeOptionService.findAll(), ItemNotFoundException.class.getSimpleName());

        final ProgrammingTimeOptionResponseDTO allProgrammingTimeOptionsFound = programmingTimeOptionService.findAll();

        assertNotNull(allProgrammingTimeOptionsFound);
        assertNotNull(allProgrammingTimeOptionsFound.getProgrammingTimeOptions());

        assertEquals(
                programmingTimeOptionListMockData.size(),
                allProgrammingTimeOptionsFound.getProgrammingTimeOptions().size()
        );

        assertTrue(
                allProgrammingTimeOptionsFound.getProgrammingTimeOptions()
                        .contains(programmingTimeOptionMockData.getProgrammingTime())
        );
    }

    @Test
    @DisplayName("Should thrown ItemNotFoundException when no programming time options have been found")
    void findAllProgrammingTimeOptionsShouldThrownNoItemFoundExceptionWhenNoOptionsWereFound() {
        when(programmingTimeOptionRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> programmingTimeOptionService.findAll());
    }
}
