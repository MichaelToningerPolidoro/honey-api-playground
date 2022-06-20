package com.honeyautomation.apiplayground.controllers;

import com.honeyautomation.apiplayground.dto.HobbyDTO;
import com.honeyautomation.apiplayground.services.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hobbies")
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<HobbyDTO> findAll() {
        return ResponseEntity.ok(hobbyService.findAll());
    }
}
