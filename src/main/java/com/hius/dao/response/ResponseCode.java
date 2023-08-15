package com.hius.dao.response;

public enum ResponseCode implements IResponseCode{
    SUCCESS(200, "SUCCESS"),
    CREATED(201, "CREATED"),

    NO_CONTENT(204, "NO_CONTENT"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    VALIDATE_FAILED(404, "VALIDATE_FAILED"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    private Integer code = 0;
    private String message;

    private ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
