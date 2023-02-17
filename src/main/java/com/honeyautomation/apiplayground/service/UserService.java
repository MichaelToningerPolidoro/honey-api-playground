package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.factory.LocalDateFactory;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        final ProgrammingTimeOption programmingTimeOption = programmingTimeOptionService
                .findProgrammingTime(registerRequestDTO.getProgrammingTime().trim());

        final List<Hobby> hobbies = hobbyService.findHobbies(registerRequestDTO.getHobbies());
        final Country country = countryService.findCountry(registerRequestDTO.getBornData().getCountry().trim());
        final LocalDate bornDate = LocalDateFactory.getLocalDate(registerRequestDTO.getBornData().getDate().trim());

        final User userToRegister = User.builder()
                .nickName(registerRequestDTO.getNickName().trim())
                .name(registerRequestDTO.getName().trim())
                .email(registerRequestDTO.getEmail().trim().toLowerCase())
                .password(new Password(registerRequestDTO.getPassword()))
                .programmingTimeOption(programmingTimeOption)
                .bornData(new BornData(bornDate, country))
                .hobbies(hobbies)
                .build();

        userRepository.save(userToRegister);
    }
}
