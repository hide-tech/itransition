package com.yazukov.proj.advicecontroller;

import com.yazukov.proj.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AdviceControllerRest {

    @ExceptionHandler(CollectionNotFoundException.class)
    public ResponseEntity<?> handleCollectionNotFoundException(CollectionNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("change login");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDevMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("change login");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDevMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(UnsupportOperationException.class)
    public ResponseEntity<?> handleUnsupportOperationException(UnsupportOperationException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("change login");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDevMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("change login");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDevMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("change login");
        errorDetails.setDetail(ex.getMessage());
        errorDetails.setDevMessage(ex.getClass().getName());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
