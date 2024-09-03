package com.example.bankmanagement.ControllerAdvice;

import com.example.bankmanagement.Api.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity NullPointerException(NullPointerException e)
    {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
