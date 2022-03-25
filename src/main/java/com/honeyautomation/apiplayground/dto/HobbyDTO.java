package com.honeyautomation.apiplayground.dto;

import com.honeyautomation.apiplayground.entities.Hobby;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class HobbyDTO implements Serializable {

    private List<String> hobbies;

    public HobbyDTO() {}

    public HobbyDTO(List<Hobby> hobbies) {
        this.hobbies = hobbies.stream().map(Hobby::getHobby).collect(Collectors.toList());
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
