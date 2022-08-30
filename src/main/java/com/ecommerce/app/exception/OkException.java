package com.ecommerce.app.exception;

public class OkException extends RuntimeException{

    private String massage;

    public OkException() {
    }

    public OkException(String massage) {
        super(massage);
        this.massage = massage;
    }
}
