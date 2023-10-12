package com.honey.apiplayground.service;

import com.honey.apiplayground.creator.HobbyCreator;
import com.honey.apiplayground.domain.Hobby;
import com.honey.apiplayground.dto.response.HobbyResponseDTO;
import com.honey.apiplayground.exception.type.ItemNotFoundException;
import com.honey.apiplayground.exception.type.ItemNotRegisteredException;
import com.honey.apiplayground.repository.HobbyRepository;
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
class HobbyServiceTest {

    @InjectMocks
    private HobbyService hobbyService;
    @Mock
    private HobbyRepository hobbyRepositoryMock;

    @Test
    @DisplayName("Find all hobbies should return a list successfully")
    void findAllHobbiesShouldReturnAList() {
        final Hobby hobbyMockData = HobbyCreator.validHobby();
        final List<Hobby> hobbyListMockData = List.of(hobbyMockData);
        when(hobbyRepositoryMock.findAll()).thenReturn(hobbyListMockData);

        assertDoesNotThrow(() -> hobbyService.findAll(), ItemNotFoundException.class.getSimpleName());

        final HobbyResponseDTO allHobbiesFound = hobbyService.findAll();

        assertNotNull(allHobbiesFound);
        assertNotNull(allHobbiesFound.getHobbies());
        assertEquals(hobbyListMockData.size(), allHobbiesFound.getHobbies().size());
        assertTrue(allHobbiesFound.getHobbies().contains(hobbyMockData.getHobby()));
    }

    @Test
    @DisplayName("Find hobby should return a value successfully")
    void findHobbyShouldReturnAValueSuccessfully() {
        final Hobby hobbyMock = HobbyCreator.validHobby();

        when(hobbyRepositoryMock.findAllByHobbyIn(any())).thenReturn(List.of(hobbyMock));

        final List<Hobby> retrievedHobbies = hobbyService.findHobbies(List.of("testing"));

        assertNotNull(retrievedHobbies);
        assertFalse(retrievedHobbies.isEmpty());
        assertEquals(hobbyMock, retrievedHobbies.get(0));
    }

    @Test
    @DisplayName("Find no existent hobby should thrown ItemNotRegisteredException")
    void findHobbyNoExistentShouldThrownItemNotRegisteredException() {
        when(hobbyRepositoryMock.findAllByHobbyIn(any())).thenReturn(Collections.emptyList());

        assertThrows(ItemNotRegisteredException.class, () -> hobbyService.findHobbies(List.of("any")));
    }

    @Test
    @DisplayName("Should throw ItemNotFoundException when no hobbies have been found")
    void findAllHobbiesShouldThrownItemNotFoundExceptionWhenNoHobbiesWereFound() {
        when(hobbyRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ItemNotFoundException.class, () -> hobbyService.findAll());
    }
}