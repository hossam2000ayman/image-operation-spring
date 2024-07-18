package com.example.storagesystem.exception.handler;

import com.example.storagesystem.exception.ImageAlreadyExistException;
import com.example.storagesystem.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ImageAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> handleImageAlreadyExistException(ImageAlreadyExistException imageAlreadyExistException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), imageAlreadyExistException.getMessage());
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAnyOtherException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }
}
