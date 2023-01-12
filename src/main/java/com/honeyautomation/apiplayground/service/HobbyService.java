package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.HobbyRepository;
import com.honeyautomation.apiplayground.response.HobbyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Transactional(readOnly = true)
    public HobbyResponseDTO findAll() {
        final HobbyResponseDTO hobbyResponseDTO = new HobbyResponseDTO(hobbyRepository.findAll());

        if (hobbyResponseDTO.getHobbies().isEmpty()) {
            throw new ItemNotFoundException("None hobby was found ...");
        }

        return hobbyResponseDTO;
    }
}