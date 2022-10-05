package com.honeyautomation.apiplayground.handler;

import com.honeyautomation.apiplayground.exceptions.ItemNotFoundException;
import com.honeyautomation.apiplayground.exceptions.ItemNotFoundExceptionResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ItemNotFoundExceptionResponseTemplate> notFound(ItemNotFoundException itemNotFoundException) {
        ItemNotFoundExceptionResponseTemplate responseBody = ItemNotFoundExceptionResponseTemplate.builder()
                .message(itemNotFoundException.getMessage())
                .build();

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
