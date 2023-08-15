package com.hius.dao.response;

import com.hius.bean.EmptyVO;

public class CommonResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * unauthorized
     *
     * @return
     */
    public static <T> CommonResponse<T> unauthorized() {
        return new CommonResponse<T>(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage(), null);
    }

    public static <T> CommonResponse<T> unauthorized(String msg) {
        return new CommonResponse<T>(ResponseCode.UNAUTHORIZED.getCode(), msg, null);
    }

    public static <T> CommonResponse<T> internal_server_error() {
        return new CommonResponse<T>(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    public static <T> CommonResponse<T> internal_server_error(String msg) {
        return new CommonResponse<T>(ResponseCode.INTERNAL_SERVER_ERROR.getCode(), msg, null);
    }

    /**
     * bad request
     *
     * @return
     */
    public static <T> CommonResponse<T> bad_request() {
        return new CommonResponse<T>(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getMessage(), null);
    }

    public static <T> CommonResponse<T> bad_request(String msg) {
        return new CommonResponse<T>(ResponseCode.BAD_REQUEST.getCode(), msg, null);
    }

    public static <T> CommonResponse<T> bad_request(int code, String msg) {
        return new CommonResponse<T>(code, msg, null);
    }

    /*************************************/
    /**
     * success ret
     *
     * @param data
     * @return
     */
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), (T) new EmptyVO());
    }


    public CommonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
