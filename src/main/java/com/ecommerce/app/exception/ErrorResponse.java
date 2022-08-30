package com.ecommerce.app.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime dateTime;
    private HttpStatus httpStatus;
    private String massage;

    public ErrorResponse()
    {

    }
    public ErrorResponse(String massage) {
        this.massage=massage;
    }

    public ErrorResponse(LocalDateTime dateTime, HttpStatus httpStatus, String massage) {
        this.dateTime = dateTime;
        this.httpStatus = httpStatus;
        this.massage = massage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


}
