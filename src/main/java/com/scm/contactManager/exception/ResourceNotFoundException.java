package com.scm.contactManager.exception;

public class ResourceNotFoundException extends RuntimeException {
    String mesaage;
    public ResourceNotFoundException(String message){
        super(message);
        this.mesaage = message;
    }
}
