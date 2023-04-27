package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.creator.LocalDateCreator;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.exception.models.DataAlreadyUsedInfo;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
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

    public void create(RegisterRequestDTO registerRequestDTO) {
        FieldValidator.validate(registerRequestDTO);

        final String newUserEmail = registerRequestDTO.getEmail().trim().toLowerCase();
        final String newUserNickName = registerRequestDTO.getNickName().trim();

        checkForAlreadyUsedData(newUserEmail, newUserNickName);

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

    private void checkForAlreadyUsedData(String email, String nickName) {
        final List<DataAlreadyUsedInfo> dataAlreadyInUse = new ArrayList<>();

        if (isEmailAlreadyUsed(email)) {
            dataAlreadyInUse.add(new DataAlreadyUsedInfo("email"));
        }

        if (isNickNameAlreadyUsed(nickName)) {
            dataAlreadyInUse.add(new DataAlreadyUsedInfo("nickName"));
        }

        if (!dataAlreadyInUse.isEmpty()) {
            throw new DataAlreadyUsedException(dataAlreadyInUse);
        }
    }

    private boolean isEmailAlreadyUsed(String email) {
        return userRepository.countByEmail(email) > 0;
    }

    private boolean isNickNameAlreadyUsed(String nickName) {
        return userRepository.countByNickName(nickName) > 0;
    }
}
