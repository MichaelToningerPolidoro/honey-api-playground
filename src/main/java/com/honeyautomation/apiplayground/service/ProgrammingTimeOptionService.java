package com.honeyautomation.apiplayground.service;

import com.honeyautomation.apiplayground.constants.Endpoints;
import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.domain.ProgrammingTimeOption;
import com.honeyautomation.apiplayground.dto.response.ProgrammingTimeOptionResponseDTO;
import com.honeyautomation.apiplayground.exception.models.Resource;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.exception.type.ItemNotRegisteredException;
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

    @Transactional(readOnly = true)
    public ProgrammingTimeOption findProgrammingTime(String programmingTimeOption) {
        final ProgrammingTimeOption programmingTime = programmingTimeOptionRepository.findByProgrammingTime(programmingTimeOption);

        if (programmingTime == null) {
            final Resource resource = new Resource(
                    Endpoints.METHOD_FIND_ALL_PROGRAMMING_TIME_OPTIONS,
                    Endpoints.REQUEST_MAPPING_PROGRAMMING_TIME_OPTIONS
            );

            throw new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_PROGRAMMING_TIME_OPTION, resource);
        }

        return programmingTime;
    }
}
