package com.honey.apiplayground.exception.type;

public class InvalidLoginTokenException extends RuntimeException {

    public InvalidLoginTokenException(String message) {
        super(message);
    }
}
