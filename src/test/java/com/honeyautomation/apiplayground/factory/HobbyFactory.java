package com.honeyautomation.apiplayground.factory;

import com.honeyautomation.apiplayground.domain.Hobby;

public class HobbyFactory {

    public static Hobby validHobby() {
        return new Hobby(1, "Programming");
    }
}
