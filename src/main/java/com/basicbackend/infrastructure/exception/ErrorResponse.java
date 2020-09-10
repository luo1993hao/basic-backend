package com.basicbackend.infrastructure.exception;

public class ErrorResponse {
    private final ErrorRepresentation error;

    private ErrorResponse(ErrorRepresentation error) {
        this.error = error;
    }

    public static ErrorResponse errorResponse(ErrorRepresentation error) {
        return new ErrorResponse(error);
    }


    public static ErrorResponse errorResponse(AppException ex, String path) {
        return new ErrorResponse(ErrorRepresentation.from(ex, path));
    }

    public ErrorRepresentation getError() {
        return error;
    }
}
