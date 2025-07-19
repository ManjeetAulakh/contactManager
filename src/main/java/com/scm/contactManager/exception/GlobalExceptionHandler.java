package com.scm.contactManager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // as already catch in pageContrllor
    
    // @ExceptionHandler(UserAlreadyExists.class)
    // public ResponseEntity<Map<String, String>> userAlreadyExistsHandler(UserAlreadyExists e){

    //     Map<String,String> errors = new HashMap<>();
    //     errors.put(e.getFieldName(), e.getMessage());
    //     return ResponseEntity.badRequest().body(errors);
    // }   

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> ResourceNotFoundHandler(ResourceNotFoundException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("Some Resource not found: ", ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
