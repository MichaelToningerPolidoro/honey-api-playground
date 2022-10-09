package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.HobbyRepository;
import com.honeyautomation.apiplayground.response.HobbyResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class HobbyServiceTest {

    @InjectMocks
    private HobbyService hobbyService;
    @Mock
    private HobbyRepository hobbyRepositoryMock;

    @Test
    @DisplayName("Find all hobbies should return successfully")
    void findAllHobbiesShouldReturnAList() {
        final String hobbyNameMockData = "Programming";
        Mockito.when(hobbyRepositoryMock.findAll()).thenReturn(List.of(new Hobby(1, hobbyNameMockData)));

        Assertions.assertDoesNotThrow(() -> {
            hobbyService.findAll();
        }, ItemNotFoundException.class.getSimpleName());

        final HobbyResponseDTO allHobbies = hobbyService.findAll();

        Assertions.assertNotNull(allHobbies);
        Assertions.assertNotNull(allHobbies.getHobbies());
        Assertions.assertEquals(allHobbies.getHobbies().size(), 1);
        Assertions.assertTrue(allHobbies.getHobbies().contains(hobbyNameMockData));
    }

    @Test
    @DisplayName("Should throw ItemNotFoundException when no hobbies were found")
    void findAllHobbiesShouldThrownItemNotFoundExceptionWhenNoHobbiesWereFound() {
        Mockito.when(hobbyRepositoryMock.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(ItemNotFoundException.class, () -> hobbyService.findAll());
    }
}