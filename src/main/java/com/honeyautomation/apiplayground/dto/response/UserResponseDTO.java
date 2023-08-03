package com.honeyautomation.apiplayground.dto.response;

import com.honeyautomation.apiplayground.domain.BornData;
import com.honeyautomation.apiplayground.domain.Hobby;
import com.honeyautomation.apiplayground.domain.User;
import com.honeyautomation.apiplayground.dto.model.BornDataDTO;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDTO implements Serializable {

    private final String nickname;
    private final String name;
    private final String email;
    private final String programmingTime;
    private final BornDataDTO bornDataDTO;
    private final List<String> hobbies;

    public UserResponseDTO(User user) {
        nickname = user.getNickName();
        name = user.getName();
        email = user.getEmail(); // TODO: Mask email when it be added
        programmingTime = user.getProgrammingTimeOption().getProgrammingTime();
        hobbies = user.getHobbies().stream().map(Hobby::getHobby).collect(Collectors.toList());

        final BornData userBornData = user.getBornData();
        bornDataDTO = new BornDataDTO(userBornData.getDate().toString(), userBornData.getCountry().getCountry());
    }

}
