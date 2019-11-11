package com.reactivespring.errorhandling;

import com.reactivespring.exceptions.DataNotFoundException;
import com.reactivespring.model.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataExceptionController {

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException ex) {
        System.out.println("Exception thrown: " + ex.getMessage());

        ResponseStructure responseStructure = new ResponseStructure();

        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }

}
