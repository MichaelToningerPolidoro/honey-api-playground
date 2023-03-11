package com.honeyautomation.apiplayground.handler;

import com.honeyautomation.apiplayground.exception.details.InternalServerErrorDetails;
import com.honeyautomation.apiplayground.exception.details.ItemNotFoundExceptionDetails;
import com.honeyautomation.apiplayground.exception.details.ItemNotRegisteredExceptionDetails;
import com.honeyautomation.apiplayground.exception.details.ValidationExceptionDetails;
import com.honeyautomation.apiplayground.exception.models.Resource;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.exception.type.ItemNotRegisteredException;
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

    @ExceptionHandler(ItemNotRegisteredException.class)
    public ResponseEntity<ItemNotRegisteredExceptionDetails> itemNotRegistered(ItemNotRegisteredException itemNotRegisteredException) {
        final Resource resource = new Resource(
                itemNotRegisteredException.getResource().getMethod(),
                itemNotRegisteredException.getResource().getEndpoint()
        );

        final ItemNotRegisteredExceptionDetails responseBody = ItemNotRegisteredExceptionDetails.builder()
                .message(itemNotRegisteredException.getMessage())
                .resource(resource)
                .build();

        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<InternalServerErrorDetails> internalServerError (Exception internalServerErrorException) {
        return new ResponseEntity<>(new InternalServerErrorDetails(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
