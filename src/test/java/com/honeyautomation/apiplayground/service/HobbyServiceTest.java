package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.factory.HobbyFactory;
import com.honeyautomation.apiplayground.repository.HobbyRepository;
import com.honeyautomation.apiplayground.response.HobbyResponseDTO;
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
class HobbyServiceTest {

    @InjectMocks
    private HobbyService hobbyService;
    @Mock
    private HobbyRepository hobbyRepositoryMock;

    @Test
    @DisplayName("Find all hobbies should return successfully")
    void findAllHobbiesShouldReturnAList() {
        final Hobby hobbyMockData = HobbyFactory.validHobby();
        when(hobbyRepositoryMock.findAll()).thenReturn(List.of(hobbyMockData));

        assertDoesNotThrow(() -> {
            hobbyService.findAll();
        }, ItemNotFoundException.class.getSimpleName());

        final HobbyResponseDTO allHobbiesFound = hobbyService.findAll();

        assertNotNull(allHobbiesFound);
        assertNotNull(allHobbiesFound.getHobbies());
        assertEquals(allHobbiesFound.getHobbies().size(), 1);
        assertTrue(allHobbiesFound.getHobbies().contains(hobbyMockData.getHobby()));
    }

    @Test
    @DisplayName("Should throw ItemNotFoundException when no hobbies were found")
    void findAllHobbiesShouldThrownItemNotFoundExceptionWhenNoHobbiesWereFound() {
        when(hobbyRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> hobbyService.findAll());
    }
}