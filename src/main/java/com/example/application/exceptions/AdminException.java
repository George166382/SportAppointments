package com.example.application.exceptions;

public class AdminException extends Exception {
    public AdminException(String message) {
        super(message);
    }

  @Override
  public String toString()
  {
    return "AdminException: {message = '" + getMessage() + "'}";
  }
}
