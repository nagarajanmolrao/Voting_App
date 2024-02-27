package com.sample.apps.voting_app.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpParserException(HttpMessageNotReadableException ex,
                                                                         HttpServletRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setStatus(HttpStatus.BAD_REQUEST.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(cer, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpMessageConversionException(HttpMessageConversionException ex, HttpServletRequest request){
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(cer, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityException(DataIntegrityViolationException ex){
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(cer, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<CustomErrorResponse> handleJpaException(JpaObjectRetrievalFailureException ex,
                                                                  HttpServletRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setStatus(HttpStatus.BAD_REQUEST.value());
        cer.setMsg(ex.getMessage());
        cer.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(cer, HttpStatus.BAD_REQUEST);
    }
}
