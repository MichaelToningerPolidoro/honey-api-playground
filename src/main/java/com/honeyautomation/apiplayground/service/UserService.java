package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.creator.LocalDateCreator;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
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

        checkUserDataAvailability(newUserEmail, newUserNickName);

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

    public User findUserByEmail(String email) {
        final User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ItemNotFoundException(ExceptionMessages.NOT_FOUND_USER);
        }

        return user;
    }

    public UserResponseDTO getUserData(String loginToken) {
        tokenService.validateLoginToken(loginToken);

        final User user = findUserByEmail(tokenService.getLoginSubject(loginToken));

        return new UserResponseDTO(user);
    }

    private void checkUserDataAvailability(String email, String nickName) {
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

    private boolean isEmailAlreadyUsed(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isNickNameAlreadyUsed(String nickName) {
        return userRepository.existsByNickName(nickName);
    }
}
