package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.domain.*;
import com.honeyautomation.apiplayground.dto.request.RegisterRequestDTO;
import com.honeyautomation.apiplayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_USER)
public class UserController {

    // TODO: add the service class
    //  Add a body
    //  change the method return type

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        final Function<Integer, Integer> random = (max) -> ThreadLocalRandom.current().nextInt(1, max);
        final Password password = Password.builder().password("dawdwa").build();
        final ProgrammingTimeOption programmingTimeOption = new ProgrammingTimeOption(random.apply(5), "some");
        final BornData bornData = BornData.builder()
                .date(new Date(4, Calendar.JULY, 1994))
                .country(new Country(random.apply(225), "ddd", "ddd"))
                .build();
        final List<Hobby> hobbies = List.of(new Hobby(random.apply(6), "dwad"), new Hobby(random.apply(6), "wdaw"));

        userRepository.save(
                User.builder()
                        .name("Joaquim " + System.currentTimeMillis())
                        .email("someemail@gmail.com")
                        .password(password)
                        .programmingTimeOption(programmingTimeOption)
                        .bornData(bornData)
                        .hobbies(hobbies)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
