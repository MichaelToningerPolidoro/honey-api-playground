package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.factory.LocalDateFactory;
import com.honeyautomation.apiplayground.repository.CountryRepository;
import com.honeyautomation.apiplayground.repository.HobbyRepository;
import com.honeyautomation.apiplayground.repository.ProgrammingTimeOptionRepository;
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
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    public void create(RegisterRequestDTO registerRequestDTO) {
        final ProgrammingTimeOption programmingTimeOption = programmingTimeOptionRepository
                .findByProgrammingTime(registerRequestDTO.getProgrammingTime().trim());
        // TODO: check data here

        final Country country = countryRepository.findByCountry(registerRequestDTO.getBornData().getCountry().trim());
        // TODO: check data here

        final List<Hobby> hobbies = hobbyRepository.findAllByHobbyIn(registerRequestDTO.getHobbies());
        // TODO: check data here

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
