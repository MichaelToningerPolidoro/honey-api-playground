package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.factory.LocalDateFactory;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(RegisterRequestDTO registerRequestDTO) {
        final LocalDate bornDate = LocalDateFactory.getLocalDate(registerRequestDTO.getBornData().getDate());

        // TODO: get programming time from database
        final ProgrammingTimeOption programmingTimeOption = new ProgrammingTimeOption(1, registerRequestDTO.getProgrammingTime());

        // TODO: get country from database
        final Country country = new Country(15, "Brazil", "ISO");

        // TODO: get hobbies from database
        final List<Hobby> hobbies = registerRequestDTO.getHobbies().stream()
                .map(hobby -> new Hobby(1, "some hobby"))
                .collect(Collectors.toList());

        final User userToRegister = User.builder()
                .nickName(registerRequestDTO.getNickName())
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .password(new Password(registerRequestDTO.getPassword()))
                .programmingTimeOption(programmingTimeOption)
                .bornData(new BornData(bornDate, country))
                .hobbies(hobbies)
                .build();

        userRepository.save(userToRegister);
    }
}
