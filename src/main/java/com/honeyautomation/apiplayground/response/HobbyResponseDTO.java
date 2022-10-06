package com.honeyautomation.apiplayground.response;

import com.honeyautomation.apiplayground.domain.Hobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class HobbyResponseDTO implements Serializable {

    private List<String> hobbies;

    public HobbyResponseDTO(List<Hobby> hobbies) {
        this.hobbies = hobbies.stream().map(Hobby::getHobby).collect(Collectors.toList());
    }
}
