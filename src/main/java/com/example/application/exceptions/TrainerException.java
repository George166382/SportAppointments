package com.example.application.exceptions;

public class TrainerException extends Exception {
    public TrainerException(String message) {
        super(message);
    }



    @Override
    public String toString()
    {
        return "TrainerException: {message = '" + getMessage() + "'}";
    }
}
