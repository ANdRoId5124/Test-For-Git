package com.pet.project.authorization.exception;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final String message) {
    super(message);
  }
}
