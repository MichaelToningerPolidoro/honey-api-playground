package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(RegisterRequestDTO registerRequestDTO) {
        final User userToRegister = User.builder()
                .nickName(registerRequestDTO.getNickName())
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .password(new Password(registerRequestDTO.getPassword()))
                // FIXME: Get id here refering the getProgrammingTime() value
                .programmingTimeOption(new ProgrammingTimeOption(1, registerRequestDTO.getProgrammingTime()))
                // FIXME: Adjust here
                .bornData(new BornData(new Date(1970, 1, 1), new Country(15, "Brazil", "ISO")))
                // FIXME: Change hobby Id from reference of database
                .hobbies(registerRequestDTO.getHobbies().stream().map(hobby -> new Hobby(1, "some hobby")).collect(Collectors.toList()))
                .build();

        userRepository.save(userToRegister);
    }
}
