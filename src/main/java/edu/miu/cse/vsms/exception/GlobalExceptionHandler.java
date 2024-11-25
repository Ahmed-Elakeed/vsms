package edu.miu.cse.vsms.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
