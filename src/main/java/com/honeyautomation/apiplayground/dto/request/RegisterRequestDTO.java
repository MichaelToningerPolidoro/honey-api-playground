package com.honeyautomation.apiplayground.dto.request;

import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterRequestDTO {

    private String name;
    private String email;
    private String password;
    private String programmingTime;
    private BornDataDTO bornData;
    private List<String> hobbies;

}
