package com.ecommerce.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException extends RuntimeException{

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handelNotFoundException(NotFoundException exception)
    {
        String msg= exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND,msg);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handelNotNullException(BadRequestException exception)
    {
        String msg=exception.getMessage();
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST,msg);

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //Category Already exist
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handelAlreadyExistsException(AlreadyExistsException exception)
    {
        String msg=exception.getMessage();
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.CONFLICT,msg);

        return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(OkException.class)
    public ResponseEntity<Object> handelOkException(OkException exception)
    {
        String msg=exception.getMessage();
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMassage(msg);

        return  ResponseEntity.status(HttpStatus.OK).body(errorResponse);
    }


}
