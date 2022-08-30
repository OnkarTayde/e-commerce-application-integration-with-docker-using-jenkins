package com.ecommerce.app.exception;

public class AlreadyExistsException extends RuntimeException{

    private String massage;

    public AlreadyExistsException() {
    }

    public AlreadyExistsException(String massage) {
        super(massage);
        this.massage = massage;
    }
}
