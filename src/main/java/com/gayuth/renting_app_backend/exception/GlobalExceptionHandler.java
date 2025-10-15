package com.gayuth.renting_app_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(new ApiError("USER_NOT_FOUND", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiError> handleInvalidPassword(InvalidPasswordException ex) {
        return new ResponseEntity<>(new ApiError("INCORRECT_PASSWORD", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnverifiedAccountException.class)
    public ResponseEntity<ApiError> handleUnverifiedAccount(UnverifiedAccountException ex) {
        return new ResponseEntity<>(new ApiError("UNVERIFIED_EMAIL", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        return new ResponseEntity<>(new ApiError("INTERNAL_ERROR", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

