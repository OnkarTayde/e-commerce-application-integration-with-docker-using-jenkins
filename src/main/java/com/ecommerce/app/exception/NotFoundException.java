package com.ecommerce.app.exception;

public class NotFoundException extends RuntimeException {

   private String massage;

    public NotFoundException() {
    }

    public NotFoundException(String massage) {
        super(massage);
        this.massage = massage;
    }
}
