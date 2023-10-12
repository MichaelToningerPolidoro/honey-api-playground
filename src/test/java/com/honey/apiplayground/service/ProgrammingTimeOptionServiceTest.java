package com.honey.apiplayground.service;

import com.honey.apiplayground.creator.ProgrammingTimeOptionCreator;
import com.honey.apiplayground.domain.ProgrammingTimeOption;
import com.honey.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honey.apiplayground.exception.type.ItemNotFoundException;
import com.honey.apiplayground.exception.type.ItemNotRegisteredException;
import com.honey.apiplayground.repository.ProgrammingTimeOptionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProgrammingTimeOptionServiceTest {

    @InjectMocks
    private ProgrammingTimeOptionService programmingTimeOptionService;
    @Mock
    private ProgrammingTimeOptionRepository programmingTimeOptionRepositoryMock;

    @Test
    @DisplayName("Find all programming time options should return a list successfully")
    void findAllProgrammingTimeOptionsShouldReturnAList() {
        final ProgrammingTimeOption programmingTimeOptionMockData = ProgrammingTimeOptionCreator.validProgrammingTimeOption();
        final List<ProgrammingTimeOption> programmingTimeOptionListMockData = List.of(programmingTimeOptionMockData);

        when(programmingTimeOptionRepositoryMock.findAll()).thenReturn(programmingTimeOptionListMockData);

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
    @DisplayName("Find programming time option should return a value successfully")
    void findProgrammingTimeShouldReturnAValueSuccessfully() {
        final ProgrammingTimeOption programmingTimeOptionMock = ProgrammingTimeOptionCreator.validProgrammingTimeOption();

        when(programmingTimeOptionRepositoryMock.findByProgrammingTime(any())).thenReturn(programmingTimeOptionMock);

        final ProgrammingTimeOption retrievedProgrammingTimeOption = programmingTimeOptionService
                .findProgrammingTime(programmingTimeOptionMock.getProgrammingTime());

        assertNotNull(retrievedProgrammingTimeOption);
        assertEquals(programmingTimeOptionMock, retrievedProgrammingTimeOption);
    }

    @Test
    @DisplayName("Find no existent programming time option should thrown ItemNotRegisteredException")
    void findProgrammingTimeOptionNoExistentShouldThrownItemNotRegisteredException() {
        when(programmingTimeOptionRepositoryMock.findByProgrammingTime(any())).thenReturn(null);

        assertThrows(ItemNotRegisteredException.class, () -> programmingTimeOptionService.findProgrammingTime("any"));
    }

    @Test
    @DisplayName("Should thrown ItemNotFoundException when no programming time options have been found")
    void findAllProgrammingTimeOptionsShouldThrownNoItemFoundExceptionWhenNoOptionsWereFound() {
        when(programmingTimeOptionRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> programmingTimeOptionService.findAll());
    }
}
