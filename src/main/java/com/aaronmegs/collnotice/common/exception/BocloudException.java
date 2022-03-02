package com.aaronmegs.collnotice.common.exception;

import lombok.Data;

@Data
public class BocloudException extends RuntimeException {
    private int code;
    private String message;

    public BocloudException() {
    }

    public BocloudException(BaseExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMsg();
    }

    public BocloudException(String message) {
        super(message);
        this.message = message;
    }

    public BocloudException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public BocloudException(String message, Throwable cause) {
        super(message, cause);
    }

    public BocloudException(Throwable cause) {
        super(cause);
    }

    public BocloudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BocloudException(BaseExceptionCode exceptionCode, Throwable cause) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMsg();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
