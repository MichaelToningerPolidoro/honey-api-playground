package com.honeyautomation.apiplayground.handler;

import com.honeyautomation.apiplayground.exception.ItemNotFoundException;
import com.honeyautomation.apiplayground.exception.ItemNotFoundExceptionDetails;
import com.honeyautomation.apiplayground.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ItemNotFoundExceptionDetails> notFound(ItemNotFoundException itemNotFoundException) {
        final ItemNotFoundExceptionDetails responseBody = ItemNotFoundExceptionDetails.builder()
                .message(itemNotFoundException.getMessage())
                .build();

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationExceptionDetails>> fieldValidationError(MethodArgumentNotValidException methodArgumentNotValidException) {
        final List<ValidationExceptionDetails> responseBody = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> ValidationExceptionDetails.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList())
        ;

        return ResponseEntity.badRequest().body(responseBody);
    }
}
