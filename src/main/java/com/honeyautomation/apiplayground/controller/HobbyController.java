package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.dto.response.HobbyResponseDTO;
import com.honeyautomation.apiplayground.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_HOBBY)
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<HobbyResponseDTO> findAll() {
        return new ResponseEntity<>(hobbyService.findAll(), HttpStatus.OK);
    }
}
