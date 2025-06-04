package com.plaza.traceability.domain.exception;

public enum ExceptionResponse {

    VALIDATION_CUSTOMER("Debe ingresar el cliente");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

