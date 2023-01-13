package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.ProgrammingTimeOptionRepository;
import com.honeyautomation.apiplayground.response.ProgrammingTimeOptionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProgrammingTimeOptionService {

    @Autowired
    private ProgrammingTimeOptionRepository programmingTimeOptionRepository;

    @Transactional(readOnly = true)
    public ProgrammingTimeOptionResponseDTO findAll() {
        final ProgrammingTimeOptionResponseDTO programmingTimeOptionResponseDTO =
                new ProgrammingTimeOptionResponseDTO(programmingTimeOptionRepository.findAll());

        if (programmingTimeOptionResponseDTO.getProgrammingTimeOptions().isEmpty()) {
            throw new ItemNotFoundException("None programming time option was found ...");
        }

        return programmingTimeOptionResponseDTO;
    }
}
