package com.scm.contactManager.exception;

public class UserAlreadyExists extends RuntimeException {
    private String message;
    private String fieldName;
    
    public UserAlreadyExists(String fieldName, String message){
        super(message);
        this.message = message;
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }

    public String getMessage(){
        return message;
    }
}
