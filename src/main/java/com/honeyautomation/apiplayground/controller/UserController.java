package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.dto.request.RegisterDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_USER)
public class UserController {

    // TODO: add the service class
    //  Add a body
    //  change the method return type

    @PostMapping
    public void register(@RequestBody RegisterDTO registerDTO) {

    }
}
