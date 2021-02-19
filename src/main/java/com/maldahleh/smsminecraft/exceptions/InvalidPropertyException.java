package com.maldahleh.smsminecraft.exceptions;

public class InvalidPropertyException extends Exception {
  public InvalidPropertyException(String property) {
    super("'" + property + "' is required.");
  }
}
