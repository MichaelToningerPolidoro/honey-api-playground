package com.honey.apiplayground.service;

import com.honey.apiplayground.constants.Endpoints;
import com.honey.apiplayground.constants.ExceptionMessages;
import com.honey.apiplayground.domain.Hobby;
import com.honey.apiplayground.dto.response.HobbyResponseDTO;
import com.honey.apiplayground.exception.models.Resource;
import com.honey.apiplayground.exception.type.ItemNotFoundException;
import com.honey.apiplayground.exception.type.ItemNotRegisteredException;
import com.honey.apiplayground.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HobbyService {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Transactional(readOnly = true)
    public HobbyResponseDTO findAll() {
        final HobbyResponseDTO hobbyResponseDTO = new HobbyResponseDTO(hobbyRepository.findAll());

        if (hobbyResponseDTO.getHobbies().isEmpty()) {
            throw new ItemNotFoundException(ExceptionMessages.NOT_FOUND_HOBBY);
        }

        return hobbyResponseDTO;
    }

    @Transactional(readOnly = true)
    public List<Hobby> findHobbies(List<String> hobbies) {
        final List<Hobby> allHobbiesFound = hobbyRepository.findAllByHobbyIn(hobbies);

        final boolean allHobbiesFoundSuccessfully = allHobbiesFound != null
                && !allHobbiesFound.isEmpty()
                && allHobbiesFound.size() == hobbies.size()
                ;

        if (!allHobbiesFoundSuccessfully) {
            final Resource resource = new Resource(Endpoints.METHOD_FIND_ALL_HOBBIES, Endpoints.REQUEST_MAPPING_HOBBY);
            throw new ItemNotRegisteredException(ExceptionMessages.NOT_REGISTERED_HOBBY, resource);
        }

        return allHobbiesFound;
    }
}
