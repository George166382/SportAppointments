package com.example.application.exceptions;

public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }

    @Override
    public String toString()
    {
        return "UserException: {message = '" + getMessage() + "'}";
    }
}

