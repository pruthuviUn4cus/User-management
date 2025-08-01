package com.example.user.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("User not found");
        return new ResponseEntity<>(exceptionDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DuplicateValueException.class)
    public ResponseEntity<Object> handleDuplicateValueException(DuplicateValueException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Trying to duplicate a unique value: "+exception.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }
}
