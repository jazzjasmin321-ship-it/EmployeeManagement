package com.example.employeemanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String, Object>> handleNotFound(
        ResourceNotFoundException exception) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 404);
    response.put("error", "Not Found");
    response.put("message", exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
}

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<Map<String, Object>> handleIllegalArgument(
        IllegalArgumentException exception) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 400);
    response.put("error", "Bad Request");
    response.put("message", exception.getMessage());

    return ResponseEntity.badRequest().body(response);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(
        MethodArgumentNotValidException exception) {

    Map<String, Object> errors = new HashMap<>();

    exception.getBindingResult().getFieldErrors().forEach(error ->
            errors.putIfAbsent(error.getField(), error.getDefaultMessage()));

    Map<String, Object> response = new HashMap<>();

    response.put("status", 400);
    response.put("error", "Validation Failed");
    response.put("messages", errors);

    return ResponseEntity.badRequest().body(response);
}

@ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<Map<String, Object>> handleInvalidRequestBody(
        HttpMessageNotReadableException exception) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 400);
    response.put("error", "Bad Request");
    response.put("message",
            "Invalid request body. Please check your JSON and data types.");

    return ResponseEntity.badRequest().body(response);
}

@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<Map<String, Object>> handleDatabaseConflict(
        DataIntegrityViolationException exception) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 409);
    response.put("error", "Conflict");
    response.put("message",
            "The operation conflicts with existing database data.");

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
}


}
