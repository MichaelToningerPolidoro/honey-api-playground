package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.exception.models.Resource;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
import com.honeyautomation.apiplayground.exception.type.ItemNotRegisteredException;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.honeyautomation.apiplayground.creator.CountryCreator.validCountry;
import static com.honeyautomation.apiplayground.creator.HobbyCreator.validHobby;
import static com.honeyautomation.apiplayground.creator.ProgrammingTimeOptionCreator.validProgrammingTimeOption;
import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.invalidRegisterRequestDTO;
import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.validRegisterRequestDTO;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private ProgrammingTimeOptionService programmingTimeOptionServiceMock;

    @Mock
    private CountryService countryServiceMock;

    @Mock
    private HobbyService hobbyServiceMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    @DisplayName("Register new user should be successfully inserted")
    void registerNewUserShouldBeSuccessfullyInserted() {
        when(userRepositoryMock.existsByEmail(any())).thenReturn(false);
        when(userRepositoryMock.existsByNickName(any())).thenReturn(false);
        when(programmingTimeOptionServiceMock.findProgrammingTime(any())).thenReturn(validProgrammingTimeOption());
        when(countryServiceMock.findCountry(any())).thenReturn(validCountry());
        when(hobbyServiceMock.findHobbies(any())).thenReturn(List.of(validHobby()));
        when(userRepositoryMock.save(any())).thenReturn(null);

        assertDoesNotThrow(() -> userService.create(validRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an invalid user should throw ConstraintViolationException")
    void registerAnInvalidUserShouldThrowConstraintViolationException() {
        assertThrows(ConstraintViolationException.class, () -> userService.create(invalidRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an used email should throw DataAlreadyUsedException")
    void registerAnUsedEmailShouldThrowDataAlreadyUsedException() {
        when(userRepositoryMock.existsByEmail(any())).thenReturn(true);
        assertThrows(DataAlreadyUsedException.class, () -> userService.create(validRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an used nickName should throw DataAlreadyUsedException")
    void registerAnUsedNickNameShouldThrowDataAlreadyUsedException() {
        when(userRepositoryMock.existsByNickName(any())).thenReturn(true);
        assertThrows(DataAlreadyUsedException.class, () -> userService.create(validRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an user with non registered programming time should throw ItemNotRegisteredException")
    void registerUserWithNonRegisteredProgrammingTimeShouldThrowItemNotRegisteredException() {
        final Resource resource = new Resource(
                Endpoints.METHOD_FIND_ALL_PROGRAMMING_TIME_OPTIONS,
                Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS
        );

        when(programmingTimeOptionServiceMock.findProgrammingTime(any()))
                .thenThrow(new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_PROGRAMMING_TIME_OPTION, resource));

        assertThrows(ItemNotRegisteredException.class, () -> userService.create(validRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an user with non registered hobby should throw ItemNotRegisteredException")
    void registerUserWithNonRegisteredHobbyShouldThrowItemNotRegisteredException() {
        final Resource resource = new Resource(Endpoints.METHOD_FIND_ALL_HOBBIES, Endpoints.REQUEST_MAPPING_HOBBY);

        when(hobbyServiceMock.findHobbies(any()))
                .thenThrow(new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_HOBBY, resource));

        assertThrows(ItemNotRegisteredException.class, () -> userService.create(validRegisterRequestDTO()));
    }

    @Test
    @DisplayName("Register an user with non registered country should throw ItemNotRegisteredException")
    void registerUserWithNonRegisteredCountryShouldThrowItemNotRegisteredException() {
        final Resource resource = new Resource(Endpoints.METHOD_FIND_ALL_COUNTRIES, Endpoints.REQUEST_MAPPING_COUNTRIES);

        when(countryServiceMock.findCountry(any()))
                .thenThrow(new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_COUNTRY, resource));

        assertThrows(ItemNotRegisteredException.class, () -> userService.create(validRegisterRequestDTO()));
    }
}
