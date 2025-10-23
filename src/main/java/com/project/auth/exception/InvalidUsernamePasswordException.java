package com.project.auth.exception;

public class InvalidUsernamePasswordException extends RuntimeException {
    public InvalidUsernamePasswordException(String message) {
        super(message);
    }
}
