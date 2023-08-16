package com.hius.exception;

public class RestException extends RuntimeException{

    public RestException(String message) {
        super(message);
    }

    public RestException(String status, String message) {
        super(status, new Throwable(message));
    }

}
