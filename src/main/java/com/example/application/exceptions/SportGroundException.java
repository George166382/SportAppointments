package com.example.application.exceptions;

public class SportGroundException extends Exception {
    public SportGroundException(String message) {
        super(message);
    }



    @Override
    public String toString()
    {
        return "SportGroundException: {message = '" + getMessage() + "'}";
    }
}
