package com.honeyautomation.apiplayground.handler;

import com.honeyautomation.apiplayground.constants.ExceptionMessages;
import com.honeyautomation.apiplayground.exception.details.ItemNotRegisteredExceptionDetails;
import com.honeyautomation.apiplayground.exception.details.OnlyMessageDetails;
import com.honeyautomation.apiplayground.exception.details.StandardErrorDetails;
import com.honeyautomation.apiplayground.exception.models.Resource;
import com.honeyautomation.apiplayground.exception.type.DataAlreadyUsedException;
import com.honeyautomation.apiplayground.exception.type.InvalidCredentialsException;
import com.honeyautomation.apiplayground.exception.type.ItemNotFoundException;
import com.honeyautomation.apiplayground.exception.type.ItemNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<StandardErrorDetails>> fieldValidationError(ConstraintViolationException constraintViolationException) {
        final List<StandardErrorDetails> responseBody = constraintViolationException.getConstraintViolations()
                .stream()
                .map(constraintViolation -> StandardErrorDetails.builder()
                        .field(constraintViolation.getPropertyPath().toString())
                        .message(constraintViolation.getMessage())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAlreadyUsedException.class)
    public ResponseEntity<List<StandardErrorDetails>> dataAlreadyInUse(DataAlreadyUsedException dataAlreadyUsedException) {
        final List<StandardErrorDetails> responseBody = dataAlreadyUsedException.getDataAlreadyUsed()
                .stream()
                .map(data -> StandardErrorDetails.builder()
                        .field(data)
                        .message(ExceptionMessages.DATA_ALREADY_USED)
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<OnlyMessageDetails> invalidCredentials(InvalidCredentialsException invalidCredentialsException) {
        return new ResponseEntity<>(new OnlyMessageDetails(invalidCredentialsException.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<OnlyMessageDetails> notFound(ItemNotFoundException itemNotFoundException) {
        return new ResponseEntity<>(new OnlyMessageDetails(itemNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
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

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<OnlyMessageDetails> httpMessageNotReadable (HttpMessageNotReadableException HttpMessageNotReadableException) {
        return new ResponseEntity<>(new OnlyMessageDetails(ExceptionMessages.HTTP_MESSAGE_NOT_READABLE), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OnlyMessageDetails> internalServerError (Exception internalServerErrorException) {
        return new ResponseEntity<>(new OnlyMessageDetails(ExceptionMessages.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
