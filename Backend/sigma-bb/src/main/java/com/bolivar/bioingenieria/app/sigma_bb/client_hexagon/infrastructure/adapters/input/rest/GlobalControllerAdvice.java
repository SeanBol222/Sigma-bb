package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest;

import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception.PersonNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public ErrorResponse handlePersonNotFoundException() {
        return ErrorResponse.builder()
                .code(PERSON_NOT_FOUND.getCode())
                .message(PERSON_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();

        return ErrorResponse.builder()
                .code(INVALID_PERSON_DATA.getCode())
                .message(INVALID_PERSON_DATA.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericException(Exception ex) {
        return ErrorResponse.builder()
                .code(UNKNOWN_ERROR.getCode())
                .message(UNKNOWN_ERROR.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
