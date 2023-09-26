package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.dto.response.UserResponseDTO;
import com.honeyautomation.apiplayground.exception.models.Resource;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.exception.type.ItemNotRegisteredException;
import com.honeyautomation.apiplayground.repository.UserRepository;
import com.honeyautomation.apiplayground.utils.Mask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static com.honeyautomation.apiplayground.creator.CountryCreator.validCountry;
import static com.honeyautomation.apiplayground.creator.HobbyCreator.validHobby;
import static com.honeyautomation.apiplayground.creator.ProgrammingTimeOptionCreator.validProgrammingTimeOption;
import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.invalidRegisterRequestDTO;
import static com.honeyautomation.apiplayground.creator.RegisterRequestDTOCreator.validRegisterRequestDTO;
import static com.honeyautomation.apiplayground.creator.UpdateUserRequestDTOCreator.validUpdateUserRequest;
import static com.honeyautomation.apiplayground.creator.UserCreator.validUser;
import static org.junit.jupiter.api.Assertions.*;
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

    @Mock
    private TokenService tokenServiceMock;

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
    @DisplayName("Find user by email should return a user successfully")
    void findUserByEmailShouldReturnAUserSuccessfully() {
        when(userRepositoryMock.findByEmail(any())).thenReturn(validUser());
        assertDoesNotThrow(() -> userService.findUserByEmail("anyemail@testing.com"));
    }

    @Test
    @DisplayName("Find user data should return user data successfully")
    void findUserDataShouldReturnUserDataSuccessfully() {
        final User mockUser = validUser();

        when(tokenServiceMock.getLoginSubject(any())).thenReturn(mockUser.getEmail());
        when(userRepositoryMock.findByEmail(any())).thenReturn(mockUser);

        final UserResponseDTO retrievedUserData = userService.getUserData("SomeLoginToken");

        assertNotNull(retrievedUserData);
        assertEquals(mockUser.getNickName(), retrievedUserData.getNickname());
        assertEquals(mockUser.getName(), retrievedUserData.getName());
        assertEquals(Mask.getMaskedEmail(mockUser.getEmail()), retrievedUserData.getEmail());
        assertEquals(mockUser.getProgrammingTimeOption().getProgrammingTime(), retrievedUserData.getProgrammingTime());
        assertEquals(mockUser.getBornData().getCountry().getCountry(), retrievedUserData.getBornDataDTO().getCountry());
        assertEquals(mockUser.getBornData().getDate().toString(), retrievedUserData.getBornDataDTO().getDate());

        final List<String> expectedHobbies = mockUser.getHobbies().stream().map(Hobby::getHobby).collect(Collectors.toList());
        assertIterableEquals(expectedHobbies, retrievedUserData.getHobbies());
    }

    @Test
    @DisplayName("Updating user data should be successfully updated")
    void updatingUserDataShouldBeSuccessfullyUpdated() {
        when(userRepositoryMock.existsByNickName(any())).thenReturn(false);
        when(userRepositoryMock.findByEmail(any())).thenReturn(validUser());
        when(programmingTimeOptionServiceMock.findProgrammingTime(any())).thenReturn(validProgrammingTimeOption());
        when(countryServiceMock.findCountry(any())).thenReturn(validCountry());
        when(hobbyServiceMock.findHobbies(any())).thenReturn(List.of(validHobby()));
        when(userRepositoryMock.save(any())).thenReturn(null);

        final String loginTokenMock = "Some login token";
        assertDoesNotThrow(() -> userService.update(loginTokenMock, validUpdateUserRequest()));
    }

    @Test
    @DisplayName("Find user by email should thrown exception")
    void findUserByEmailShouldThrownException() {
        when(userRepositoryMock.findByEmail(any())).thenReturn(null);
        assertThrows(ItemNotFoundException.class, () -> userService.findUserByEmail("anyemail@testing.com"));
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
    @DisplayName("Update an used nickName should thrown DataAlreadyUsedException")
    void updateAnUsedNickNameShouldThrownDataAlreadyUsedException() {
        final String loginTokenMock = "Some Login Token";
        when(userRepositoryMock.existsByNickName(any())).thenReturn(true);

        assertThrows(DataAlreadyUsedException.class, () -> userService.update(loginTokenMock, validUpdateUserRequest()));
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
