package com.honeyautomation.apiplayground.controller;

import com.honeyautomation.apiplayground.response.HobbyResponseDTO;
import com.honeyautomation.apiplayground.service.HobbyService;
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
    public ResponseEntity<HobbyResponseDTO> findAll() {
        return ResponseEntity.ok(hobbyService.findAll());
    }
}
