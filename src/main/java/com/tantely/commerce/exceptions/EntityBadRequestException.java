package com.tantely.commerce.exceptions;

public class EntityBadRequestException extends RuntimeException{
    public EntityBadRequestException(String message) {
        super(message);
    }
}
