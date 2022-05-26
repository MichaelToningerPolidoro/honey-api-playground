package com.honeyautomation.apiplayground.services;

import com.honeyautomation.apiplayground.dto.HobbyDTO;
import com.honeyautomation.apiplayground.repositories.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Transactional(readOnly = true)
    public HobbyDTO findAll() {
        return new HobbyDTO(hobbyRepository.findAll());
    }
}
