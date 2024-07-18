package com.example.storagesystem.exception;

public class ImageAlreadyExistException extends RuntimeException {
    public ImageAlreadyExistException(String message) {
        super(message);
    }
}
