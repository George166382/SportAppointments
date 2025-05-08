package com.example.application.exceptions;

public class AppointmentException extends Exception {
    public AppointmentException(String message) {
        super(message);
    }

    @Override
    public String toString()
    {
        return "AppointmentException: {message = '" + getMessage() + "'}";
    }
}
