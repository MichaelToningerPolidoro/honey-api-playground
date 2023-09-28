package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.annotation.RequiresLoginTokenValidation;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.LocalDateCreator;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.dto.request.UpdateUserRequestDTO;
import com.honeyautomation.apiplayground.dto.response.UserResponseDTO;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.UserRepository;
import com.honeyautomation.apiplayground.validation.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProgrammingTimeOptionService programmingTimeOptionService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private TokenService tokenService;

    public void create(RegisterRequestDTO registerRequestDTO) {
        FieldValidator.validate(registerRequestDTO);

        final String newUserEmail = registerRequestDTO.getEmail().trim().toLowerCase();
        final String newUserNickName = registerRequestDTO.getNickName().trim();

        checkUserDataAvailabilityForRegister(newUserEmail, newUserNickName);

        final ProgrammingTimeOption programmingTimeOption = programmingTimeOptionService
                .findProgrammingTime(registerRequestDTO.getProgrammingTime().trim());

        final List<Hobby> hobbies = hobbyService.findHobbies(registerRequestDTO.getHobbies());
        final Country country = countryService.findCountry(registerRequestDTO.getBornData().getCountry().trim());
        final LocalDate bornDate = LocalDateCreator.getLocalDate(registerRequestDTO.getBornData().getDate().trim());

        final User userToRegister = User.builder()
                .nickName(newUserNickName)
                .name(registerRequestDTO.getName().trim())
                .email(newUserEmail)
                .password(new Password(registerRequestDTO.getPassword()))
                .programmingTimeOption(programmingTimeOption)
                .bornData(new BornData(bornDate, country))
                .hobbies(hobbies)
                .build();

        userRepository.save(userToRegister);
    }

    @RequiresLoginTokenValidation
    public UserResponseDTO getUserData(String loginToken) {
        final User user = findUserByEmail(tokenService.getLoginSubject(loginToken));

        return new UserResponseDTO(user);
    }

    @RequiresLoginTokenValidation
    public void update(String loginToken, UpdateUserRequestDTO updateUserRequestDTO) {
        FieldValidator.validate(updateUserRequestDTO);

        final User retrievedUser = findUserByEmail(tokenService.getLoginSubject(loginToken));

        checkUserDataAvailabilityForUpdate(updateUserRequestDTO.getNickName().trim());
        final ProgrammingTimeOption newProgrammingTimeOption = programmingTimeOptionService
                .findProgrammingTime(updateUserRequestDTO.getProgrammingTime().trim());

        final List<Hobby> newHobbies = hobbyService.findHobbies(updateUserRequestDTO.getHobbies());
        final Country newCountry = countryService.findCountry(updateUserRequestDTO.getBornData().getCountry().trim());
        final LocalDate newBornDate = LocalDateCreator.getLocalDate(updateUserRequestDTO.getBornData().getDate().trim());

        final User userWithNewData = User.builder()
                .id(retrievedUser.getId())
                .nickName(updateUserRequestDTO.getNickName().trim())
                .name(updateUserRequestDTO.getName().trim())
                .email(retrievedUser.getEmail())
                .password(new Password(updateUserRequestDTO.getPassword()))
                .programmingTimeOption(newProgrammingTimeOption)
                .bornData(new BornData(newBornDate, newCountry))
                .hobbies(newHobbies)
                .build();

        userRepository.save(userWithNewData);
    }

    @RequiresLoginTokenValidation
    public void delete(String loginToken) {
        try {
            userRepository.delete(findUserByEmail(tokenService.getLoginSubject(loginToken)));
        } catch (Exception e) {
            // TODO: Implement log message here
        }
    }

    public User findUserByEmail(String email) {
        final User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ItemNotFoundException(ExceptionMessages.NOT_FOUND_USER);
        }

        return user;
    }

    private void checkUserDataAvailabilityForRegister(String email, String nickName) {
        final List<String> dataAlreadyInUse = new ArrayList<>();

        if (isEmailAlreadyUsed(email)) {
            dataAlreadyInUse.add("email");
        }

        if (isNickNameAlreadyUsed(nickName)) {
            dataAlreadyInUse.add("nickName");
        }

        if (!dataAlreadyInUse.isEmpty()) {
            throw new DataAlreadyUsedException(dataAlreadyInUse);
        }
    }

    private void checkUserDataAvailabilityForUpdate(String nickName) {
        if (isNickNameAlreadyUsed(nickName)) throw new DataAlreadyUsedException(List.of("nickName"));
    }

    private boolean isEmailAlreadyUsed(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isNickNameAlreadyUsed(String nickName) {
        return userRepository.existsByNickName(nickName);
    }
}
