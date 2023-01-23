package com.honeyautomation.apiplayground.dto.response;

import com.honeyautomation.apiplayground.domain.Hobby;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HobbyResponseDTO implements Serializable {

    private final List<String> hobbies;

    public HobbyResponseDTO(List<Hobby> hobbies) {
        this.hobbies = hobbies.stream().map(Hobby::getHobby).collect(Collectors.toList());
    }
}
