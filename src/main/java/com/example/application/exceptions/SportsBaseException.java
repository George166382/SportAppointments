package com.example.application.exceptions;

public class SportsBaseException extends Exception {
  public SportsBaseException(String message) {
    super(message);
  }



  @Override
  public String toString()
  {
    return "SportsBaseException: {message = '" + getMessage() + "'}";
  }
}
