package com.honeyautomation.apiplayground.service;

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

    // nesse daqui que eu posso retornar true caso exists .. assim mudando o comportamento do Service
    @Mock
    private UserRepository userRepositoryMock;

    @Test
    @DisplayName("Register new user should be successfully inserted")
    void registerNewUserShouldBeSuccessfullyInserted() {
        when(programmingTimeOptionServiceMock.findProgrammingTime(any())).thenReturn(validProgrammingTimeOption());
        when(countryServiceMock.findCountry(any())).thenReturn(validCountry());
        when(hobbyServiceMock.findHobbies(any())).thenReturn(List.of(validHobby()));
        when(userRepositoryMock.save(any())).thenReturn(null);

        assertDoesNotThrow(() -> userService.create(validRegisterRequestDTO()));
    }

    //FIXME: Change to an ParametrizedTest
    @Test
    @DisplayName("Register an invalid user should throw ConstraintViolationException")
    void registerAnInvalidUserShouldThrowConstraintViolationException() {
        assertThrows(ConstraintViolationException.class, () -> userService.create(invalidRegisterRequestDTO()));
    }


}
