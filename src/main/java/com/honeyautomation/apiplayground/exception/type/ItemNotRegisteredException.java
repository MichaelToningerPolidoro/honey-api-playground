package com.honeyautomation.apiplayground.exception.type;

import com.honeyautomation.apiplayground.exception.models.Resource;
import lombok.Getter;

@Getter
public class ItemNotRegisteredException extends RuntimeException {

    private final Resource resource;

    public ItemNotRegisteredException(String message, Resource resource) {
        super(message);
        this.resource = resource;
    }
}
