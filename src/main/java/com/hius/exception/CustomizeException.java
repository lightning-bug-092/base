package com.hius.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor

@Builder
public class CustomizeException extends RuntimeException{
    protected String code;
    protected String error;
    protected String message;
    protected Object data;
    protected HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public CustomizeException() {
    }

    public CustomizeException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public CustomizeException(String code, String message, String error, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.error = error;
        this.httpStatus = httpStatus;
    }

    public CustomizeException(String code, String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
    public CustomizeException(String code, String message, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public CustomizeException(String code, String message, HttpStatus httpStatus, Object data) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(String code, String message) {
        this.message = message;
        this.code = code;
    }



    public CustomizeException(String message, Exception ex) {
        this.message = message + "\n" + ex.getMessage();

    }

    public CustomizeException(Exception ex) {
        super(ex);
    }

    public CustomizeException(String message, CustomizeException ex) {
        this.message = message + "\n" + ex.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
