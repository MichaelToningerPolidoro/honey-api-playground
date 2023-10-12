package com.honey.apiplayground.controller;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honey.apiplayground.service.ProgrammingTimeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS)
public class ProgrammingTimeOptionController {

    @Autowired
    private ProgrammingTimeOptionService programmingTimeOptionService;

    @GetMapping
    public ResponseEntity<ProgrammingTimeOptionResponseDTO> findAll() {
        return new ResponseEntity<>(programmingTimeOptionService.findAll(), HttpStatus.OK);
    }
}
