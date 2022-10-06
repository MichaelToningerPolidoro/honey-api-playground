package com.honeyautomation.apiplayground.dto;

import com.honeyautomation.apiplayground.entities.Hobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class HobbyDTO implements Serializable {

    private List<String> hobbies;

    public HobbyDTO(List<Hobby> hobbies) {
        this.hobbies = hobbies.stream().map(Hobby::getHobby).collect(Collectors.toList());
    }
}
