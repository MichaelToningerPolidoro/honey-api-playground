package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.response.ProgrammingTimeOptionResponseDTO;
import com.honeyautomation.apiplayground.service.ProgrammingTimeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(programmingTimeOptionService.findAll());
    }
}
