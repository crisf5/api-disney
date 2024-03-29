package com.disney.api.controller;

import com.disney.api.dto.ApiErrorDTO;
import com.disney.api.exception.IncorrectLogin;
import com.disney.api.exception.ParamNotFound;
import com.disney.api.exception.UserExist;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<Object> HandleThrowable(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList()
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), errorDTO.getStatus(), request);
    }

    @ExceptionHandler(value = {UserExist.class})
    protected ResponseEntity<Object> HandleUserExist(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("User already exists")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), errorDTO.getStatus(), request);
    }

    @ExceptionHandler(value = {IncorrectLogin.class})
    protected ResponseEntity<Object> HandleIncorrectLogin(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("Incorrect Login")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), errorDTO.getStatus(), request);
    }

    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> HandleParamNotFound(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("Param Not Found")
        );
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), errorDTO.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiErrorDTO errorDTO = new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getStatus(), request);
    }
}
