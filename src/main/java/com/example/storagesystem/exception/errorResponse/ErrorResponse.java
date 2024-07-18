package com.example.storagesystem.exception.errorResponse;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, int statusCode, String message) {
}
