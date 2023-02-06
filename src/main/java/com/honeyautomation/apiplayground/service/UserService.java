package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.General;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void create(RegisterRequestDTO registerRequestDTO) {
        // FIXME add a factory to abstract this creation
        final String[] splitRawDate = registerRequestDTO.getBornData().getDate().split(General.DATE_SEPARATOR);
        final LocalDate bornDate = LocalDate.of(parseInt(splitRawDate[0]), parseInt(splitRawDate[1]), parseInt(splitRawDate[2]))
                .atStartOfDay(General.STANDARD_ZONE_ID)
                .toLocalDate();

        final User userToRegister = User.builder()
                .nickName(registerRequestDTO.getNickName())
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .password(new Password(registerRequestDTO.getPassword()))
                // FIXME: Get id here refering the getProgrammingTime() value
                .programmingTimeOption(new ProgrammingTimeOption(1, registerRequestDTO.getProgrammingTime()))
                // FIXME: Adjust here
                .bornData(new BornData(bornDate, new Country(15, "Brazil", "ISO")))
                // FIXME: Change hobby Id from reference of database
                .hobbies(registerRequestDTO.getHobbies().stream().map(hobby -> new Hobby(1, "some hobby")).collect(Collectors.toList()))
                .build();

        userRepository.save(userToRegister);
    }
}
