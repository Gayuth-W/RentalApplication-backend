package com.gayuth.renting_app_backend.exception;

public class UnverifiedAccountException extends RuntimeException {
    public UnverifiedAccountException(String message) {
        super(message);
    }
}
