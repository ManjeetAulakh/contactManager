package com.scm.contactManager.exception;

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
}
