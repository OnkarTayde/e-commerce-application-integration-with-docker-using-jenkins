package com.ecommerce.app.exception;

public class BadRequestException extends RuntimeException {


    private String massage;
    public BadRequestException() {
    }

    public BadRequestException(String massage) {
        super(massage);
        this.massage = massage;
    }
}

