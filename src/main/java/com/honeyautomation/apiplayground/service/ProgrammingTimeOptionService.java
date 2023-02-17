package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.repository.ProgrammingTimeOptionRepository;
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
            throw new ItemNotFoundException(ExceptionMessages.NOT_FOUND_PROGRAMMING_TIME_OPTION);
        }

        return programmingTimeOptionResponseDTO;
    }
}
