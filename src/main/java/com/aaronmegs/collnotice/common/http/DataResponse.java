package com.aaronmegs.collnotice.common.http;

import com.aaronmegs.collnotice.common.exception.BaseExceptionCode;
import com.aaronmegs.collnotice.common.exception.BocloudException;
import com.aaronmegs.collnotice.common.exception.StateEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int SUCCESS = 200;
    private String message;
    private String exceptionStackTrace;
    private String state;
    private int code;
    private T data;
    private T entity;

    private DataResponse() {
    }

    private DataResponse(String state, int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.state = state;
        this.data = data;
    }

    private DataResponse(String state, int code, String message, String exceptionStackTrace, T data) {
        this.code = code;
        this.message = message;
        this.exceptionStackTrace = exceptionStackTrace;
        this.state = state;
        this.data = data;
    }

    private DataResponse(String state, int code, String message, T data, T entity) {
        this.code = code;
        this.message = message;
        this.state = state;
        this.data = data;
        this.entity = entity;
    }

    private DataResponse(BaseExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMsg();
    }

    public static <T> DataResponse<T> success() {
        return success(null);
    }

    public static <T> DataResponse<T> success(T result) {
        return new DataResponse(StateEnum.SUCCESS.getState(), SUCCESS, (String) null, result);
    }

    public static <T> DataResponse<T> success(T result, T entity) {
        return new DataResponse(StateEnum.SUCCESS.getState(), SUCCESS, (String) null, result, entity);
    }

    public static <T> DataResponse<T> info(BaseExceptionCode exceptionCode) {
        return info(exceptionCode, null);
    }

    public static <T> DataResponse<T> info(BaseExceptionCode exceptionCode, T result) {
        return new DataResponse(StateEnum.INFO.getState(), exceptionCode.getCode(), exceptionCode.getMsg(), result);
    }

    public static <T> DataResponse<T> error(BaseExceptionCode exceptionCode) {
        return error(exceptionCode, null);
    }

    public static <T> DataResponse<T> error(BaseExceptionCode exceptionCode, T result) {
        return new DataResponse(StateEnum.ERROR.getState(), exceptionCode.getCode(), exceptionCode.getMsg(), result);
    }

    public static <T> DataResponse<T> error(BaseExceptionCode exceptionCode, String exceptionStackTrace, T result) {
        return new DataResponse(StateEnum.ERROR.getState(), exceptionCode.getCode(), exceptionCode.getMsg(), exceptionStackTrace, result);
    }

    public static <T> DataResponse<T> warn(BaseExceptionCode exceptionCode) {
        return new DataResponse(StateEnum.WARN.getState(), exceptionCode.getCode(), exceptionCode.getMsg(), (Object) null);
    }

    public static <T> DataResponse<T> warn(BocloudException exceptionCode) {
        return new DataResponse(StateEnum.WARN.getState(), exceptionCode.getCode(), exceptionCode.getMessage(), (Object) null);
    }

    public static <T> DataResponse<T> warn(BaseExceptionCode exceptionCode, T result) {
        return new DataResponse(StateEnum.WARN.getState(), exceptionCode.getCode(), exceptionCode.getMsg(), result);
    }

    public static <T> DataResponse<T> defined(StateEnum state, int code, String message, T data) {
        return new DataResponse(state.getState(), code, message, data);
    }

    public static <T> DataResponse<T> defined(StateEnum state, int code, String message, String exceptionStackTrace, T data) {
        return new DataResponse(state.getState(), code, message, exceptionStackTrace, data);
    }

    public String getMessage() {
        return this.message;
    }

    public String getExceptionStackTrace() {
        return this.exceptionStackTrace;
    }

    public String getState() {
        return this.state;
    }

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public T getEntity() {
        return this.entity;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExceptionStackTrace(String exceptionStackTrace) {
        this.exceptionStackTrace = exceptionStackTrace;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

}
